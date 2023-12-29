package com.ecommerce.repository.springdata.adapters;


import com.ecommerce.exception.UserNotFound;
import com.ecommerce.model.cart.Cart;
import com.ecommerce.model.cart.CartItem;
import com.ecommerce.model.user.User;
import com.ecommerce.port.adapters.repositories.CartRepositoryPort;
import com.ecommerce.repository.springdata.entity.CartEntity;
import com.ecommerce.repository.springdata.entity.CartItemEntity;
import com.ecommerce.repository.springdata.entity.UserEntity;
import com.ecommerce.repository.springdata.mappers.CartItemMapper;
import com.ecommerce.repository.springdata.mappers.CartMapper;
import com.ecommerce.repository.springdata.repository.CartJpaRepository;
import com.ecommerce.repository.springdata.repository.UserJpaRepository;
import com.ecommerce.security.AuthenticationAdapter;
import com.ecommerce.util.message.ErrorMessages;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CartRepositoryJpaAdapter implements CartRepositoryPort {

    private CartJpaRepository cartJpaRepository;
    private CartMapper cartMapper;
    private CartItemMapper cartItemMapper;
    private UserJpaRepository userJpaRepository;
    private AuthenticationAdapter authenticationAdapter;

    public CartRepositoryJpaAdapter(CartJpaRepository cartJpaRepository, CartMapper cartMapper, CartItemMapper cartItemMapper, UserJpaRepository userJpaRepository, AuthenticationAdapter authenticationAdapter) {
        this.cartJpaRepository = cartJpaRepository;
        this.cartMapper = cartMapper;
        this.cartItemMapper = cartItemMapper;
        this.userJpaRepository = userJpaRepository;
        this.authenticationAdapter = authenticationAdapter;
    }

    @Override
    public Optional<Cart> findCartByUserId(long id) {
        return cartJpaRepository.findCartByUserId(id)
                .map(this::mapCartEntityToCart);
    }

    private Cart mapCartEntityToCart(CartEntity cartEntity){
        Cart cart = cartMapper.toCart(cartEntity);
        addCartItemToCart(cartEntity, cart);
        return cart;
    }

    private void addCartItemToCart(CartEntity cartEntity, Cart cart) {
        cartEntity.getCartItemEntityList().forEach(cartItemEntity -> {
            CartItem cartItem = cartItemMapper.toCartItem(cartItemEntity);
            cart.addCartItem(cartItem);
        });
    }

    @Override
    public void save(Cart cart) {
        CartEntity cartEntity = new CartEntity();
        mapCartToCartEntity(cart, cartEntity);
        cartJpaRepository.save(cartEntity);
    }

    private void mapCartToCartEntity(Cart cart, CartEntity cartEntity) {
        cartEntity.setId(cart.getId());
        cartEntity.setTotalPrice(cart.getTotalPrice());
        cartEntity.setTotalQuantity(cart.getTotalQuantity());
        setUserEntity(cartEntity);
        setCartItemEntityList(cart, cartEntity);
    }

    private void setCartItemEntityList(Cart cart, CartEntity cartEntity) {
        List<CartItemEntity> cartItemEntityList = cart.getCartItems().stream()
                .map(cartItem -> cartItemMapper.toCartItemEntity(cartItem))
                .collect(Collectors.toList());
        cartEntity.setCartItemEntityList(cartItemEntityList);
    }

    private void setUserEntity(CartEntity cartEntity) {
        User user = authenticationAdapter.getAuthenticatedUser();
        UserEntity userEntity = userJpaRepository.findById(user.getId())
                .orElseThrow(() -> new UserNotFound(ErrorMessages.USER_NOT_FOUND));
        cartEntity.setUserEntity(userEntity);
    }
}
