package fr.chatelain.filament.model;

import java.util.List;

public class FactoryFilament {

    public FactoryFilament() {
    }

    public static Account getInstanceAccount(){
        return new Account();
    }

    public static Account getInstanceAccount(String firstName, String lastName, List<Printer> listPrinter){
        return new Account(firstName, lastName, listPrinter);
    }

    public static BrandFilament getInstanceBrandFilament(){
        return new BrandFilament();
    }

    public static BrandFilament getInstanceBrandFilament(String name, String color, boolean silk, boolean diamond, Material material, Picture picture){
        return new BrandFilament(name, color, silk, diamond, material, picture);
    }

    public static Filament getInstanceFilament(){
        return new Filament();
    }

    public static Filament getInstanceFilament(BrandFilament brand, double extrusionMultiplier, int bedTemperatureFirstLayer, int bedTemperatureOtherLayer, int extruderTemperatureFirstLayer, int extruderTemperatureOtherLayer, int lengthRetraction){
        return new Filament(brand, extrusionMultiplier, bedTemperatureFirstLayer, bedTemperatureOtherLayer, extruderTemperatureFirstLayer, extruderTemperatureOtherLayer, lengthRetraction);
    }

    public static Picture getInstancePicture(){
        return new Picture();
    }

    public static Picture getInstancePicture(String data, String name, String typeMime){
        return new Picture(data, name, typeMime);
    }

    public static Printer getInstancePrinter(){
        return new Printer();
    }

    public static Printer getInstancePrinter(String name, String model, Picture picture, List<Filament> listFilament){
        return new Printer(name, model, picture, listFilament);
    }
}
