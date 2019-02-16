package app;

import List.LinkedLista;
import Nodes.Node;

public class EjerciciosExtraSimple<T extends Comparable<T>> {

    private int count = 0;
    private long suma = 0;
    private T maximo = null;

    public boolean SonIguales(LinkedLista<T> list1, LinkedLista<T> list2) {
        if (list1.getLength() != list2.getLength()) {
            return false;
        } else {
            if (list1.getElementAt(0) != null) {
                if (Compare(list1.getElementAt(0), list2.getElementAt(0))) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
    }

    private boolean Compare(Node<T> n1, Node<T> n2) {
        if (n1.getNext() == null && n2.getNext() == null) {
            return true;
        } else {
            if (n1.getValue().equals(n2.getValue()))
                return Compare(n1.getNext(), n2.getNext());
            else {
                return false;
            }
        }
    }

    public int ExisteElemento(T value, LinkedLista<T> list) {
        count = 0;
        if (ExisteElemento(value, list.getElementAt(0)))
            return count;
        else {
            return count - 1;
        }
    }

    private boolean ExisteElemento(T value, Node<T> node) {
        if (node.getNext() == null) {
            if(node.getValue().equals(value)) count++;
            return false;
        } else {
            if (value.equals(node.getValue())) {
                return true;
            } else {
                count++;
                return ExisteElemento(value, node.getNext());
            }
        }
    }

    public int Ocurrencia(T value, LinkedLista<T> list) {
        count = 0;
        if (list.getElementAt(0) != null)
            Ocurrencia(value, list.getElementAt(0));
        return count;
    }

    private boolean Ocurrencia(T value, Node<T> node) {
        if (node.getNext() == null) {
            if (node.getValue().equals(value))
                count++;
            return true;
        } else {
            if (value.equals(node.getValue())) {
                count++;
                return Ocurrencia(value, node.getNext());
            } else {
                return Ocurrencia(value, node.getNext());
            }
        }
    }

    public long Suma(LinkedLista<Integer> list) {
        suma = 0;
        if (list.getElementAt(0) != null)
            Suma(list.getElementAt(0));
        return suma;
    }

    private boolean Suma(Node<Integer> node) {
        if (node.getNext() == null) {
            suma = suma + node.getValue();
            return true;
        } else {
            suma = suma + node.getValue();
            return Suma(node.getNext());
        }
    }

    public T Maximo(LinkedLista<T> list){
        maximo = list.getElementAt(0).getValue();
        Maximo(list.getElementAt(0));
        return maximo;
    }

    private T Maximo(Node<T> node){
        if(node.getNext() == null){
            if(node.getValue().compareTo(maximo) > 0) maximo = node.getValue();
            return maximo;
        } else {
            if(node.getValue().compareTo(maximo) > 0){
                maximo = node.getValue();
                return Maximo(node.getNext());
            } else {
                return Maximo(node.getNext());
            }
        }
    }


}
