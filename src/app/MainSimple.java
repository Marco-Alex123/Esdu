package app;

import List.LinkedLista;

public class MainSimple {

    public static LinkedLista<Integer> list1;
    public static LinkedLista<Integer> list2;

    public static void main(String[] args) {
        
        System.out.println("Ejercicios con listas simplemente enlazadas");
        
        EjerciciosExtraSimple ej = new EjerciciosExtraSimple();
        list1 = new LinkedLista<>();
        list2 = new LinkedLista<>();

        for (int i = 0; i < 20; i++) {
            list1.Add(i);
            list2.Add(i);
        }

        // Métodos extas
        System.out.println("Máximo elemento de la lista: ");
        System.out.println(ej.Maximo(list1));
        System.out.println("Verifica si las listas son iguales: ");
        System.out.println(ej.SonIguales(list1,list2));
        System.out.println("Verifica si existe elemento en la lista y regresa la posición de la primera ocurrencia: ");
        System.out.println(ej.ExisteElemento(10, list1));
        System.out.println("Verifica el numero de ocurrencias de un elemento en una lista ");
        System.out.println(ej.Ocurrencia(10, list1));
        System.out.println("Hace la suma de elementos enteros en una lista");
        System.out.println(ej.Suma((LinkedLista<Integer>)list1));
        // Métodos de la clase
        list1.Add(10);  //Añade al final
        list1.AddAt(10, 10); //Añade en posición X
        list1.AddAtStart(10); //Añade en el inicio
        list1.AddAfter(10, 10); //Añade después del valor
        list1.AddBefore(10, 10); //Añade antes del valor
        list1.Remove(10); //Retira la primera ocurrencua
        list1.RemoveBefore(10); //Retira la primera ocurrencia antes de
        list1.RemoveAfter(10); //Retira la primera ocurrrencia después de
        //Los métodos arriba invocados pueden utilizarse en un ciclo para repetición.
        list1.getElementAt(10); //Obtiene el nodo en la posición X
        list1.RemoveAll(10); //Retira todos los elementos concurrentes

    }
}
