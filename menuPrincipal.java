import java.util.ArrayList;

import javax.swing.JOptionPane;

public class menuPrincipal {
    public static void main(String[] args) {
        
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
        switch (selectedValue.toString()) {
            case "Moneda":
                magnitude = "la cantidad de dinero";
            break;
            case "Temperatura":
                magnitude = "los grados de entrada";
            break;
            case "Presión":
                magnitude = "la magnitud de presión";
            break;
        }
        
        // Solicitar repetidamente el dato inicial
        String initialQtty = new String();
        double quantity;

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

        // Obtener la selección de unidad inicial del usuario
        String[] array = qttyUnits.toArray(new String[qttyUnits.size()]);

        Object initialUnit = JOptionPane.showInputDialog(
            null,
             "Selecciona la unidad inicial",
             String.format("Conversor de Moneda - Convirtiendo %s", selectedValue.toString()),
             JOptionPane.INFORMATION_MESSAGE,
             null,
             array,
             array[0]
        );

        // remover la selección de la lista de opciones
        qttyUnits.remove(initialUnit);
        array = qttyUnits.toArray(new String[qttyUnits.size()]);

        // Obtener la selección de unidad final del usuario
        Object finalUnit = JOptionPane.showInputDialog(
            null,
             "Selecciona la unidad final",
             String.format("Conversor de Moneda - Convirtiendo %s", selectedValue.toString()),
             JOptionPane.INFORMATION_MESSAGE,
             null,
             array,
             array[0]
        );

        
    }
}
