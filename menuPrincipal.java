import javax.swing.JOptionPane;

public class menuPrincipal {
    public static void main(String[] args) {
        
        // Menú principal con las opciones de conversión
        String[] convOptions = {
            "Moneda",
            "Temperatura",
            "Presión"
        };

        Object selectedValue = JOptionPane.showInputDialog(
            null,
             "Selecciona el conversor que quieres usar",
             "Conversor de Moneda - Menú Principal",
             JOptionPane.INFORMATION_MESSAGE,
             null,
             convOptions,
             convOptions[0]
        );

        
    }
}
