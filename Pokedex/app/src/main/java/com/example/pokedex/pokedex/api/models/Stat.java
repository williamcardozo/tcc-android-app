package com.example.pokedex.pokedex.api.models;

public class Stat {
    private String name;

    public String getName() {
        switch (this.name) {
            case "hp":
                return "HP";
            case "attack":
                return "Ataque";
            case "defense":
                return "Defesa";
            case "special-attack":
                return "Ataque especial";
            case "special-defense":
                return "Defesa especial";
            case "speed":
                return "Velocidade";
            case "accuracy":
                return "Precisão";
            case "evasion":
                return "Evasão";
        }
        return this.name;
    }
}
