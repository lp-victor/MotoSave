/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AuxSerializacion;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * @author victor, Israel, David
 */
public class myOOS extends ObjectOutputStream {
    
    public myOOS(OutputStream out) throws IOException{
        super(out);
    }
    //
    @Override
    protected void writeStreamHeader()throws IOException{
        System.out.println("No hago nada");
    }
}
