package List;

import Excepciones.isEmptyException;
import Nodes.Node;

import java.util.Iterator;

public class DoubleLinkedList<T extends Comparable<T>> implements Iterable<T>, Comparable<T> {

    private Node<T> head, tail;
    private long lenght;

    public DoubleLinkedList() {
        head = new Node<>();
        tail = new Node<>();
        lenght = 0;
    }

    public DoubleLinkedList(T value) {
        this();
        Node<T> _new = new Node<>(value);
        head.setNext(_new);
        tail.setPrev(_new);
        lenght++;
    }

    public DoubleLinkedList(Node<T> node) {
        this(node.getValue());
    }


    public boolean Add(T value) {
        Node<T> _new = new Node<>(value);
        if (_new == null) return false;
        try {
            isEmpty();
            Node<T> tmp = tail.getPrev();
            tmp.setNext(_new);
            _new.setPrev(tmp);
            tail.setPrev(_new);
        } catch (isEmptyException e) {
            head.setNext(_new);
            tail.setPrev(_new);
        }
        lenght++;
        return true;
    }

    public boolean Add(Node<T> node) {
        return Add(node.getValue());
    }

    public boolean AddAtStart(T value) {
        Node<T> _new = new Node<>(value);
        try {
            isEmpty();
            Node<T> next = head.getNext();
            head.setNext(_new);
            _new.setNext(next);
            next.setPrev(_new);
        } catch (isEmptyException e) {
            head.setNext(_new);
            tail.setPrev(_new);
        }
        lenght++;
        return true;
    }

    public boolean AddAtStart(Node<T> node) {
        return AddAtStart(node.getValue());
    }

    public boolean AddAt(int position, T value) {
        try {
            isEmpty();
            Node<T> _new = new Node<>(value);
            if (position >= lenght) return false;
            else if (position == 0) {
                Node<T> next = head.getNext();
                head.setNext(_new);
                _new.setNext(next);
                next.setPrev(_new);
            } else if(position == lenght){
                Node<T> prev = tail.getPrev();
                tail.setPrev(_new);
                _new.setPrev(prev);
                prev.setNext(_new);
            } else {
                Node<T> next = getElementAt(position);
                Node<T> prev = next.getPrev();
                prev.setNext(_new);
                _new.setPrev(prev);
                _new.setNext(next);
                next.setPrev(_new);
            }
            lenght++;
            return true;
        } catch (isEmptyException e) {
            return false;
        }
    }

    public boolean AddAt(Node<T> node, int position) {
        return AddAt(position, node.getValue());
    }

    public boolean AddAfter(T after, T value) {
        try{
            isEmpty();
            Node<T> _new = new Node<>(value);
            Node<T> prev = isThereNode(head, after);
            if(prev != null){
                if(prev.getNext() == null){
                    prev.setNext(_new);
                    tail.setPrev(_new);
                    _new.setNext(null);
                } else {
                    Node<T> next = prev.getNext();
                    prev.setNext(_new);
                    _new.setPrev(prev);
                    _new.setNext(next);
                    next.setPrev(_new);
                }
                lenght++;
                return true;
            } else {
                return false;
            }
        } catch (isEmptyException e){
            return false;
        }
    }

    private Node<T> isThereNode(Node<T> node, T value){
        try{
            isEmpty();
            if(node.getNext() == null){
                if(node.getValue().equals(value)) return node;
                return null;
            }
            else if(node.getNext().getValue().equals(value)){
                return node.getNext();
            } else {
                return isThereNode(node.getNext(), value);
            }
        } catch (isEmptyException e){
            return null;
        }
    }

    public boolean AddBefore(T before, T value) {
        try{
            isEmpty();
            Node<T> _new = new Node<>(value);
            Node<T> next = isThereNode(head, before);
            if(next.getPrev() == null){
                head.setNext(_new);
                _new.setNext(next);
                next.setPrev(_new);
            } else {
                Node<T> prev = next.getPrev();
                prev.setNext(_new);
                _new.setPrev(prev);
                _new.setNext(next);
                next.setPrev(_new);
            }
            lenght++;
            return true;
        } catch (isEmptyException e){
            return false;
        }
    }

    public boolean Remove(T value) {
        try {
            isEmpty();
            Node<T> tmp = getPrevElement(head, value);
            if (tmp != null) {
                if (tmp.getNext().getNext() == null && tmp.getNext().getPrev() == null) {
                    head.setNext(null);
                    tail.setPrev(null);
                } else if (tmp.getNext().getPrev() == null) {
                    tmp.getNext().getNext().setPrev(null);
                    head.setNext(tmp.getNext().getNext());
                } else if (tmp.getNext().getNext() == null) {
                    tmp.setNext(null);
                    tail.setPrev(tmp);
                } else {
                    tmp.setNext(tmp.getNext().getNext());
                    tmp.getNext().setPrev(tmp);
                }
                lenght--;
                System.gc();
            }
            return true;
        } catch (isEmptyException e) {
            return false;
        }
    }

    public boolean Remove(Node<T> node) {
        try {
            isEmpty();
            if(node.getPrev() == null){
                Node<T> next = node.getNext();
                head.setNext(next);
                next.setPrev(null);
            } else if(node.getNext() == null) {
                Node<T> prev = node.getPrev();
                tail.setPrev(prev);
                prev.setNext(null);
            } else {
                Node<T> prev = node.getPrev();
                Node<T> next = node.getNext();
                prev.setNext(next);
                next.setPrev(prev);
            }
            System.gc();
            lenght--;
            return true;
        } catch (isEmptyException e){
            return false;
        }
    }

    public Node<T> getElementAt(int value) {
        return getElementAt(head, 0, value);
    }

    private Node<T> getElementAt(Node<T> node, int index, int value) {
        if (node.getNext() == null) {
            return null;
        } else {
            if (value == index) {
                return node.getNext();
            } else {
                if (index >= value) {
                    return null;
                } else {
                    return getElementAt(node.getNext(), ++index, value);
                }
            }
        }
    }

    public boolean RemoveAll(T value) {
        try {
            isEmpty();
            while (isThere(head, value)) {
                Remove(value);
            }
            return true;
        } catch (isEmptyException e) {
            return false;
        }
    }

    public boolean RemoveBefore(Node<T> node) {
        try{
            isEmpty();
            if(node.getPrev() == null){
                head.setNext(node);
                node.setPrev(null);
            } else {
                Node<T> prev = node.getPrev().getPrev();
                prev.setNext(node);
                node.setPrev(prev);
            }
            System.gc();
            lenght--;
            return true;
        } catch (isEmptyException e){
            return false;
        }
    }

    public boolean RemoveBefore(T value) {
        try{
            isEmpty();
            Node<T> tmp = isThereNode(head, value);
            if(tmp != null) return RemoveBefore(tmp);
            return false;
        } catch (isEmptyException e){
            return false;
        }
    }

    public boolean RemoveAfter(T value) {
        try {
            isEmpty();
            Node<T> tmp = isThereNode(head, value);
            if(tmp != null) return RemoveAfer(tmp);
            return false;
        } catch (isEmptyException e){
            return false;
        }
    }

    private boolean RemoveAfer(Node<T> node) {
        Node<T> tmp = node;
        if (tmp.getNext() == null) {
            return false;
        } else {
            if (tmp.getNext().getNext() == null) {
                tmp.setNext(null);
                tail.setPrev(tmp);
            } else {
                tmp.setNext(tmp.getNext().getNext());
                tmp.setPrev(tmp);
                tmp.getNext().setPrev(tmp);
            }
            lenght--;
            return true;
        }
    }

    private boolean isThere(Node<T> node, T value) {
        if (node.getNext() == null) {
            return false;
        } else if (node.getNext().getValue().equals(value)) {
            return true;
        } else {
            return isThere(node.getNext(), value);
        }
    }

    public Node<T> getPrevElement(Node<T> node, T value) {
        if (node.getNext().getValue().equals(value)) {
            return node;
        } else {
            if (node.getNext() == null) {
                return null;
            } else {
                return getPrevElement(node.getNext(), value);
            }
        }
    }

    public boolean isEmpty() throws isEmptyException {
        if (head.getNext() == null) {
            throw new isEmptyException("List is empty.");
        } else {
            return false;
        }
    }

    public long getLength() {
        return lenght;
    }

    public void ReversePrint() {
        ReversePrint(tail);
    }

    private void ReversePrint(Node<T> node) {
        if (node.getPrev() == null) {
            return;
        } else {
            System.out.println(node.getPrev().getValue());
        }
        ReversePrint(node.getPrev());
    }

    @Override
    public int compareTo(T t) {
        return 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> cpy = head.getNext(), sub_head;

            @Override
            public boolean hasNext() {
                if (cpy == null) {
                    return false;
                } else {
                    sub_head = cpy;
                    cpy = cpy.getNext();
                    return true;
                }
            }

            @Override
            public T next() {
                return sub_head.getValue();
            }
        };
    }
}
