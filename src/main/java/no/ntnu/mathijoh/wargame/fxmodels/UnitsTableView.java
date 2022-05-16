package no.ntnu.mathijoh.wargame.fxmodels;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import no.ntnu.mathijoh.wargame.models.units.Unit;

public class UnitsTableView extends TableView<Unit> {

    public UnitsTableView() {
        super();
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<Unit, String> unitType = new TableColumn<>("Type");
        TableColumn<Unit, String> unitName = new TableColumn<>("Name");
        TableColumn<Unit, Integer> unitHealth = new TableColumn<>("Health");
        unitType.setCellValueFactory(unit -> new ReadOnlyStringWrapper(unit.getValue().getClass().getSimpleName()));
        unitName.setCellValueFactory(unit -> new ReadOnlyStringWrapper(unit.getValue().getName()));
        unitHealth.setCellValueFactory(unit -> new ReadOnlyObjectWrapper<>(unit.getValue().getHealth()));
        getColumns().add(unitType);
        getColumns().add(unitName);
        getColumns().add(unitHealth);

    }
}
