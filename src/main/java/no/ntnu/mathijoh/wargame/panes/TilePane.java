package no.ntnu.mathijoh.wargame.panes;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import no.ntnu.mathijoh.wargame.models.map.Tile;

public class TilePane extends BorderPane implements ChangeListener<Tile> {
        
    private ObservableObjectValue<Tile> tile;
    private ImageView imageView;
    private ReadOnlyDoubleProperty parentHeightProperty;    
    private int columnSize;

    public TilePane(Tile tile) {
        super(); 
        this.tile = (new ReadOnlyObjectWrapper<>(tile));
        this.tile.addListener(this);
        this.getStylesheets().add("tile"); 
        this.drawThisTileAgainBecouseOfChangeOrCreation();  
    }

    public TilePane(Tile tile, ReadOnlyDoubleProperty parentHeighProperty, int columnSize) {
        super(); 
        this.tile = (new ReadOnlyObjectWrapper<>(tile));
        this.tile.addListener(this);
        this.getStylesheets().add("tile"); 
        this.columnSize = columnSize;
        this.parentHeightProperty = parentHeighProperty;
        this.drawThisTileAgainBecouseOfChangeOrCreation();  
    }
    
    public Tile getTile() {
        return this.tile.getValue();
    }

    public void setTile(Tile tile) {
        this.tile = new ReadOnlyObjectWrapper<>(tile);
    }

    public void drawThisTileAgainBecouseOfChangeOrCreation() {
        this.getChildren().removeAll(this.getChildren());
        if(getTile().getToken() != null) {
            imageView  = new ImageView(getTile().getToken().getImage());
            imageView.setPreserveRatio(true);
            BorderPane container = new BorderPane(imageView);
            container.getStyleClass().add("token-"+tile.getValue().getToken().getColor().toLowerCase());
            imageView.fitHeightProperty().bind(parentHeightProperty.divide(columnSize*6/4));
            container.maxHeightProperty().bind(imageView.fitHeightProperty());
            container.maxWidthProperty().bind(imageView.fitWidthProperty());
            this.setCenter(container);
        }
        this.setBackground(new Background(new BackgroundFill(Color.web(getTile().getTerrain().getColor()), null, null)));
    }

    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void changed(ObservableValue<? extends Tile> observable, Tile oldValue, Tile newValue) {
        drawThisTileAgainBecouseOfChangeOrCreation();   
    }
}