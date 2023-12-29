package com.ecommerce.service;


import com.ecommerce.model.cart.Cart;
import com.ecommerce.model.cart.CartItem;
import com.ecommerce.model.user.User;
import com.ecommerce.port.adapters.repositories.CartRepositoryPort;
import com.ecommerce.port.drivers.CartDriverPort;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CartService implements CartDriverPort {

    private final UserService userService;
    private final CartRepositoryPort cartRepository;

    public CartService(UserService userService, CartRepositoryPort cartRepository) {
        this.userService = userService;
        this.cartRepository = cartRepository;
    }

    @Override
    public Optional<Cart> getcartfromdb() {
        User user = userService.getAuthenticatedUser();
        return cartRepository.findCartByUserId(user.getId());
    }

    @Override
    public void saveCart(Cart cart) {
        Cart cartToBeUpdated = getCartFromDbOrCreateIfNotPresent();
        updateCart(cartToBeUpdated, cart);
        cartRepository.save(cartToBeUpdated);
    }

    private Cart getCartFromDbOrCreateIfNotPresent() {
        return getcartfromdb().orElseGet(Cart::new);
    }

    private void updateCart(Cart cartFromDB, Cart cart) {
        Map<Long, CartItem> cartItemMap = getCartItemsMap(cart);

        clearItemsThatAreNoLongerPresentInTheNewCart(cartFromDB, cartItemMap);

        updateCartItems(cartFromDB, cart, cartItemMap);

        cartFromDB.setTotalPrice(cart.getTotalPrice());
        cartFromDB.setTotalQuantity(cart.getTotalQuantity());
    }

    private Map<Long, CartItem> getCartItemsMap(Cart cart) {
        return cart.getCartItems().stream()
                .filter(item -> item.getProduct() != null)
                .collect(Collectors.toMap(item -> item.getProduct().getId(), item -> item));
    }
    private void clearItemsThatAreNoLongerPresentInTheNewCart(Cart cartFromDB, Map<Long, CartItem> cartItemMap) {
        cartFromDB.getCartItems().removeIf(item -> !cartItemMap.containsKey(item.getProduct().getId()));
    }

    private void updateCartItems(Cart cartFromDB, Cart cart, Map<Long, CartItem> cartItemMap) {
        cart.getCartItems().forEach(cartItem -> {
            CartItem _cartItem = cartItemMap.getOrDefault(cartItem.getProduct().getId(), null);
            if (_cartItem == null) {
                cartFromDB.addCartItem(cartItem);
            } else {
                updateExistingItem(cartItem, _cartItem);
            }
        });
    }
    private void updateExistingItem(CartItem cartItem, CartItem _cartItem) {
        _cartItem.setQuantity(cartItem.getQuantity());
        _cartItem.setAmount(cartItem.getAmount());
    }

}
