package com.example.pokedex.pokedex.api.models;

public class Type {
    private String name;


    public String getName() {
        switch (this.name) {
            case "normal":
                return "Normal";
            case "fighting":
                return "Lutador";
            case "flying":
                return "Voador";
            case "poison":
                return "Veneno";
            case "ground":
                return "Terrestre";
            case "rock":
                return "Rocha";
            case "bug":
                return "Inseto";
            case "ghost":
                return "Fantasma";
            case "steel":
                return "Aço";
            case "fire":
                return "Fogo";
            case "water":
                return "Água";
            case "grass":
                return "Grama";
            case "eletric":
                return "Elétrico";
            case "psychic":
                return "Psíquico";
            case "ice":
                return "Gelo";
            case "dragon":
                return "Dragão";
            case "dark":
                return "Escuridão";
            case "fairy":
                return "Fada";
            case "unknown":
                return "Desconhecido";
            case "shadow":
                return "Sombra";
        }

        return this.name;
    }
}
