package com.mycompany.framework_manager;

/**
 * The controller. Listens to the view and tells the model to fetch the appropriate information from the JDBC.
 *
 * @author Martin
 */
class Controller implements java.awt.event.ActionListener {

    Model model;
    View view;

    Controller() {
        System.out.println("Controller()");
    }

    /**
     * Interprets the action performed at view.
     *
     * @param e Action performed at the view.
     */
    public void actionPerformed(java.awt.event.ActionEvent e) {
        System.out.println(e.getActionCommand());

         // Incompelte: Real parameters should be fetched from view.
        switch (e.getActionCommand()) {
            case "read":
                model.read();
                break;
            case "sort": // Incomplete
                model.sort("/*Field name from view*/");
                break;
            case "add": // Incomplete
                FrameworkTemplate fmt = new FrameworkTemplate("id", "name", "type", "langauge", "intrusive", "openSource");
                model.add(fmt);
                break;
            case "remove": // Incomplete
                model.remove("id");
                break;
            case "update": // Incomplete
                model.update("fieldName", "data", "id");
                break;
            default:
                break;
        }

    }

    /**
     * Assigns a model to this controller.
     *
     * @param m Model
     */
    public void addModel(Model m) {
        this.model = m;
    }

    /**
     * Assigns a view to this controller.
     *
     * @param v View
     */
    public void addView(View v) {
        this.view = v;
    }

    /**
     * Initiates the model (by telling it to get a ResultSet from the current table).
     */
    public void initModel() {
        model.setTable();
    }

}
