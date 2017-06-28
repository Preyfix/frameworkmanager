/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.framework_manager;

/**
 * Executes the Framework Manager based on the MVC model.
 *
 * @author Martin
 */
public class RunFrameworkManager {

    public RunFrameworkManager() {

        Model model = new Model();
        View view = new View();

        model.addObserver(view);

        Controller myController = new Controller();
        myController.addModel(model);
        myController.addView(view);
        myController.initModel();

        view.addController(myController);

    }

}
