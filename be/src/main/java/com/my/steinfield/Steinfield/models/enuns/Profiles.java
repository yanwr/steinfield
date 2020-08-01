package com.my.steinfield.Steinfield.models.enuns;

public enum Profiles {
    ADMIN(1, "ROLE_ADMIN"),
    CLIENT(2, "ROLE_CLIENT");

    private int cod;
    private String desc;

    Profiles(int cod, String desc) {
        this.cod = cod;
        this.desc = desc;
    }

    public int getCod() {
        return cod;
    }

    public String getDesc() {
        return desc;
    }

    public static Profiles toEnum(Integer cod) {
        if(cod == null){
            return null;
        }
        for(Profiles profiles : Profiles.values()) {
            if(cod.equals(profiles.getCod())){
                return profiles;
            }
        }
        throw new IllegalArgumentException("Id invalid: " + cod);
    }
}