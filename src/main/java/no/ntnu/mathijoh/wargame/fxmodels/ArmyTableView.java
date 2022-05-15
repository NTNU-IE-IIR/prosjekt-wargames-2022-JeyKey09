package no.ntnu.mathijoh.wargame.fxmodels;


import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ArmyTableView extends TableView<UnitListDataHolder>{
    public ArmyTableView() {
        super();
        setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);       
        TableColumn<UnitListDataHolder, String> unitType = new TableColumn<>("Unit Type");
        TableColumn<UnitListDataHolder, Integer> unitCount = new TableColumn<>("Unit Count");
        unitType.setCellValueFactory(dataHolder -> new ReadOnlyObjectWrapper<>(dataHolder.getValue().getUnitType()));
        unitCount.setCellValueFactory(dataHolder -> new ReadOnlyObjectWrapper<>(dataHolder.getValue().getUnitCount()));
        this.getColumns().add(unitType);
        this.getColumns().add(unitCount);       
    }
    @Override
    public void refresh() {
        this.getItems().stream().forEach(unitListDataHolder -> unitListDataHolder.removeDeadUnits());
        super.refresh();
    }
}