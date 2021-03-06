import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
public class AhorcadoJaossacas {
    //numero de oportunidades del usuario designadas como objeto.
    public static int oportunidades = 6;
    
    public static void main (String[] args) throws UnsupportedEncodingException{
        String[] palabras={
            //palabras aleatorias del juego Ahorcado.
            "computador",
            "hora",
            "fuego",
            "diamante",
            "programacion"
        };
        String linea, palabra = palabras[(int) (Math.random()*palabras.length)];
        int i, n = palabra.length(), turnos = 0, aciertos = 0;
        char letra, caracter, cabeza = ' ', cuerpo = ' ', manoIzquierda = ' ', manoDerecha = ' ', pieIzquierdo = ' ', pieDerecho = ' '; 
        char[] casillas = new char[n];
        boolean encontrado;
        for(i=0; i<n; i++)
            casillas[i] = '_';
        Scanner teclado;
        PrintStream out;
        if (System.getProperties().get("os.name").equals("Windows")
                || System.console()==null){
            //lectura de lo escrito por el usuario para revisar su respuesta y si esta se encuentra entre las opciones.
            teclado = new Scanner(System.in);
            out = new PrintStream(System.out);
            } else{
            teclado = new Scanner(System.in, "CP850");
            out = new PrintStream(System.out, true, "CP850");
        }
        do{
            //condicional segun lo sucedido durante la oportunidad.
            out.println("\nOportunidades restantes: " + (oportunidades-turnos));
            for(i=0; i<n; i++)
                out.print(" " + casillas[i]);
            out.print("\nEscriba una letra: ");
            do{
                linea = teclado.nextLine();                
            } while(linea.isEmpty());
            letra = linea.charAt(0);
            encontrado = false;
            for(i=0; i<n; i++){
                caracter = palabra.charAt(i);
                if(Character.toUpperCase(letra)==Character.toUpperCase(caracter)){
                    encontrado = true;
                    if(casillas[i]=='_'){
                        casillas[i] = caracter;
                        aciertos++;
                    }
                }
            }
            if(!encontrado){
                //generador del simbolo de ahorcado segun el numero de oportunidades perdidas.
                turnos++;
                out.println("Letra no encontrada.");
                switch(turnos){
                    case 1: cabeza = 'o'; break;
                    case 2: cuerpo = 'I'; break;
                    case 3: manoDerecha = '/'; break;
                    case 4: manoIzquierda = '\\'; break;
                    case 5: pieDerecho = '/'; break;
                    case 6: pieIzquierdo = '\\'; break;
                }
            }
            out.println("     " + cabeza + " ");
            out.println("    " + manoDerecha + "" + cuerpo + "" + manoIzquierda);
            out.println("    " + pieDerecho + " " + pieIzquierdo + "\n");            
        } while(turnos<oportunidades&&aciertos<n);
        if(aciertos==n)
            out.println("Felicidades, has ganado");
        else
            out.println("Has perdido");
        out.println("La palabra correta es: " + palabra + "\n\n");
    }
}

