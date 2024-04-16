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

    public void ordenarAscendente()
    {
        LECola<E> colaAux = new LECola<E>();
        E elemento;
        E elementoAux;
        while (!this.esVacia())
        {
            elemento = this.desencolar();
            while (!colaAux.esVacia() && ((Comparable)colaAux.primero()).compareTo(elemento) > 0)
            {
                elementoAux = colaAux.desencolar();
                this.encolar(elementoAux);
            }
            colaAux.encolar(elemento);
        }
        while (!colaAux.esVacia())
        {
            elementoAux = colaAux.desencolar();
            this.encolar(elementoAux);
        }
    }

    public void toStringIterativo()
    {
        if (primero.getSiguiente() == null)
        {
            System.out.println("Lista vacia");
            return;
        }
        NodoLEG<E> actual = primero.getSiguiente();
        while (actual != null)
        {
            System.out.print(actual.getElemento() + " ");
            actual = actual.getSiguiente();
        }
        System.out.println();
    }
}
