//package com.ecommerce.repository.springdata.mappers;
//
//import com.ecommerce.model.user.Civility;
//import com.ecommerce.repository.springdata.entity.CivilityEntity;
//
//public class CivilityConverter {
//
//    public static Civility toCivility(CivilityEntity civilityEntity) {
//        if (civilityEntity == null) {
//            return null;
//        }
//        Civility civility = new Civility();
//        civility.setId(civilityEntity.getId());
//        civility.setName(civilityEntity.getName());
//        return civility;
//    }
//
//    public static CivilityEntity toCivilityEntity(Civility civility) {
//        if (civility == null) {
//            return null;
//        }
//        CivilityEntity civilityEntity = new CivilityEntity();
//        civilityEntity.setId(civility.getId());
//        civilityEntity.setName(civility.getName());
//        return civilityEntity;
//    }
//
//}
