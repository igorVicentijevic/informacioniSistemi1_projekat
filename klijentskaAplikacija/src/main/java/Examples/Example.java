/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examples;

import retrofit2.Retrofit;

/**
 *
 * @author igor
 */
public abstract class Example {
    protected Retrofit retrofit;
    private String exampleName;
    
    public Example(Retrofit retrofit){
        this(retrofit, "example");
        
    }
    
    public Example(Retrofit retrofit, String exampleName){
        this.retrofit = retrofit;
        this.exampleName = exampleName;
    }

    public abstract void exampleLogic();
    public void demonstrate(){
        System.out.println("Demonstrating "+exampleName+ "...");
        exampleLogic();
    }
}
