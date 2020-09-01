package com.tigerit.springbootcrudweb.model;

import lombok.Data;

@Data
public class Address {
    private String division;
    private String district;
    private String rmo;
    private String cityCorporationOrMunicipility;
    private String upozilla;
    private String unionOrWard;
    private String mouzaOrMoholla;
    private String additionalMouzaMoholla;
    private String wardForUnionParisad;
    private String villageOrRoad;
    private String additionalVillageRoad;
    private String homeOrHoldingNo;
    private String postOffice;
    private String postalCode;
    private String region;

    public Address(String division, String district, String rmo, String cityCorporationOrMunicipility, String upozilla, String unionOrWard, String mouzaOrMoholla, String additionalMouzaMoholla, String wardForUnionParisad, String villageOrRoad, String additionalVillageRoad, String homeOrHoldingNo, String postOffice, String postalCode, String region) {
        this.division = division;
        this.district = district;
        this.rmo = rmo;
        this.cityCorporationOrMunicipility = cityCorporationOrMunicipility;
        this.upozilla = upozilla;
        this.unionOrWard = unionOrWard;
        this.mouzaOrMoholla = mouzaOrMoholla;
        this.additionalMouzaMoholla = additionalMouzaMoholla;
        this.wardForUnionParisad = wardForUnionParisad;
        this.villageOrRoad = villageOrRoad;
        this.additionalVillageRoad = additionalVillageRoad;
        this.homeOrHoldingNo = homeOrHoldingNo;
        this.postOffice = postOffice;
        this.postalCode = postalCode;
        this.region = region;
    }

    public Address(){

    }

}
