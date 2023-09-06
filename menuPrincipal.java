import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.swing.JOptionPane;

public class menuPrincipal {
    public static void main(String[] args) {
        // para convertir necesitamos:
        double quantity; // la cantidad inicial
        double convertionFactor; // el factor de conversión
        double result; //el resultado de la conversión
        
        // Menú principal con las opciones de conversión
        String[] convOptions = {
            "Moneda",
            "Temperatura",
            "Presión"
        };

        // Obtener la selección del usuario
        Object selectedValue = JOptionPane.showInputDialog(
            null,
             "Selecciona el conversor que quieres usar",
             "Conversor de Moneda - Menú Principal",
             JOptionPane.INFORMATION_MESSAGE,
             null,
             convOptions,
             convOptions[0]
        );

        String magnitude = "";
        String folder = "";
        switch (selectedValue.toString()) {
            case "Moneda":
                magnitude = "la cantidad de dinero";
                // Ruta de la carpeta que contiene los archivos .properties
                folder = "./Moneda";
            break;
            case "Temperatura":
                magnitude = "los grados de entrada";
                folder = "./Temperatura";
            break;
            case "Presión":
                magnitude = "la magnitud de presión";
                folder = "./Presion";
            break;
        }
        
        // Solicitar repetidamente el dato inicial
        String initialQtty = new String();

        while(true){ 
            // Solicitar la cantidad
            initialQtty = JOptionPane.showInputDialog("Indica " + magnitude);
            
            // Intenta convertir la entrada a un número
            try {
                quantity = Double.parseDouble(initialQtty);
                break; //rompemos el ciclo

            } catch (NumberFormatException e) {
                // La entrada no es un número válido
                JOptionPane.showMessageDialog(
                    null,
                    "La entrada no es un número válido.",
                    "Error en Validación",
                    JOptionPane.ERROR_MESSAGE
                );
            }
        }
        
        // Menú secundario con las opciones de conversión disponibles

        // Crear una lista para almacenar los nombres de archivos .properties
        List<String> posibleConvertions = new ArrayList<>();

        // Crear un objeto File para representar la carpeta
        File file = new File(folder);

        // Verificar si la carpeta existe
        if (file.exists() && file.isDirectory()) {
            // Listar los archivos en el directorio
            File[] archivos = file.listFiles();

            // Iterar a través de los archivos y agregar los nombres de .properties a la lista
            for (File archivo : archivos) {
                if (archivo.isFile() && archivo.getName().endsWith(".properties")) {
                    String[] name = archivo.getName().split("\\.");
                    posibleConvertions.add(name[0]);
                }
            }
        } else {
            posibleConvertions.add("No hay diccionarios disponibles");
        }
        /*
        ArrayList<String> qttyUnits = new ArrayList<>();

        switch (selectedValue.toString()) {
            case "Moneda":
                qttyUnits.add("COP - Pesos Colombianos");
                qttyUnits.add("Dólares");
                qttyUnits.add("Euros");
                qttyUnits.add("Libras esterlinas");
                qttyUnits.add("Yuan Japonés");
                qttyUnits.add("Won Sur Coreano");
            break;

            case "Temperatura":
                qttyUnits.add("Farenheit");
                qttyUnits.add("Celsius");
                qttyUnits.add("Kelvin");

            break;

            case "Presión":
                qttyUnits.add("PSI - Libras por pulgada");
                qttyUnits.add("Bares");
                qttyUnits.add("atm - Atmósferas");
            break;
        };

        */

        // Obtener la selección de unidad inicial del usuario
        //String[] array = qttyUnits.toArray(new String[qttyUnits.size()]);

        Object initialUnit = JOptionPane.showInputDialog(
            null,
             "Selecciona la unidad inicial",
             String.format("Conversor de Moneda - Convirtiendo %s", selectedValue.toString()),
             JOptionPane.INFORMATION_MESSAGE,
             null,
             posibleConvertions.toArray(),
             posibleConvertions.toArray()[0]
        );

        // cargar el diccionario con las conversiones requeridas
        Properties availableConv = new Properties();
        try(InputStream inputDictionary = new FileInputStream("./dictionaries/"+initialUnit.toString()+".properties")){
            availableConv.load(inputDictionary);
            //availableConv.forEach((key, value) -> System.out.println(key + " = " + value));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                null,
                "Existe un problema al tratar de cargar el diccionario: " + e.getMessage(),
                "Error de Diccionarios",
                JOptionPane.ERROR_MESSAGE);
        }

        // remover la selección de la lista de opciones
        //qttyUnits.remove(initialUnit);

        // mostrar la lista de conversiones disponibles
        Set<Object> convDisponibles = availableConv.keySet();
        String[] keys = convDisponibles.toArray(new String[convDisponibles.size()]);
                
        // Obtener la selección de unidad final del usuario
        Object finalUnit = JOptionPane.showInputDialog(
            null,
             "Selecciona la unidad final",
             String.format("Conversor de Moneda - Convirtiendo %s", selectedValue.toString()),
             JOptionPane.INFORMATION_MESSAGE,
             null,
             keys,
             keys[0]
        );

        // el factor de conversión está en el diccionario
        System.out.println(availableConv.get(finalUnit.toString()));
        convertionFactor = Double.parseDouble(availableConv.get(finalUnit.toString()).toString());
        
        // realizar la conversión y mostrarla en pantalla
        result = quantity * convertionFactor;

        JOptionPane.showMessageDialog(
                null,
                "La cantidad resultante es: " + result,
                "Resultado de la conversión",
                JOptionPane.INFORMATION_MESSAGE);
        
    }
}
