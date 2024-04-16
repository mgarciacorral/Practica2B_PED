package Librerias.EstructurasDatos.Lineales;

import Librerias.EstructurasDatos.Modelos.I_Cola;

public class LECola<E> implements I_Cola<E>
{
    private NodoLEG<E> primero;
    private NodoLEG<E> ultimo;
    private int talla;

    public LECola()
    {
        primero = null;
        ultimo = null;
        talla = 0;
    }

    public void encolar(E elemento)
    {
        NodoLEG<E> nuevo = new NodoLEG<E>(elemento);
        if (this.esVacia())
        {
            primero = nuevo;
        }
        else
        {
            ultimo.setSiguiente(nuevo);
        }
        ultimo = nuevo;
        talla++;
    }

    public E desencolar()
    {
        if (this.esVacia())
        {
            return null;
        }
        E elemento = primero.getElemento();
        primero = primero.getSiguiente();
        talla--;
        if (this.esVacia())
        {
            ultimo = null;
        }
        return elemento;
    }

    public NodoLEG<E> getPrimero()
    {
        return primero;
    }

    public E primero()
    {
        if (this.esVacia())
        {
            return null;
        }
        return primero.getElemento();
    }

    public int getTalla()
    {
        return this.talla;
    }

    public boolean esVacia()
    {
        return primero == null;
    }

    public void toStringIterativo()
    {
        if (primero == null)
        {
            System.out.println("Lista vacia");
            return;
        }
        NodoLEG<E> actual = primero;
        while (actual != null)
        {
            System.out.println(actual.getElemento().toString());
            actual = actual.getSiguiente();
        }
        System.out.println();
    }

    public void reverse()
    {
        if(!esVacia())
        {
            E elemento = desencolar();
            reverse();
            encolar(elemento);
        }
    }

    public LECola<E> clone()
    {
        LECola<E> clon = new LECola<E>();
        for(int i = 0; i < talla; i++)
        {
            E elemento = this.desencolar();
            clon.encolar(elemento);
            this.encolar(elemento);
        }
        return clon;
    }
    public LECola<E> ordenarDesc()
    {
        LECola<E> original = clone();
        LECola<E> colaOrdenada = new LECola<E>();
        while (!this.esVacia()) {
            E elementoActual = desencolar();
            while (!colaOrdenada.esVacia() && ((Comparable)elementoActual).compareTo(colaOrdenada.primero()) > 0) {
                this.encolar(colaOrdenada.desencolar());
            }
            colaOrdenada.encolar(elementoActual);
        }

        while (!original.esVacia()) {
            this.encolar(original.desencolar());
        }

        return colaOrdenada;
    }
    public void imprimirOrdenadoDesc()
    {
        LECola<E> aux = ordenarDesc();
        aux.toStringIterativo();
    }

    public void imprimirOrdenadoAsc()
    {
        LECola<E> aux = ordenarDesc();
        aux.reverse();
        aux.toStringIterativo();
    }
}
