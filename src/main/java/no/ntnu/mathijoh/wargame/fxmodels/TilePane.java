package no.ntnu.mathijoh.wargame.fxmodels;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import no.ntnu.mathijoh.wargame.models.map.Tile;

public class TilePane extends BorderPane implements ChangeListener<Tile> {
        
    private ObservableObjectValue<Tile> tile;
    private ImageView imageView;
    private ReadOnlyDoubleProperty parentHeightProperty;    
    private int columnSize;
    private String currentClass;

    public TilePane(Tile tile) {
        super(); 
        this.tile = (new ReadOnlyObjectWrapper<>(tile));
        this.tile.addListener(this);
        this.getStylesheets().add(this.getClass().getResource("TilePane.css").toExternalForm()); 
        this.getStyleClass().add("tile");
        this.drawThisTileAgainBecouseOfChangeOrCreation();  
    }

    public TilePane(Tile tile, ReadOnlyDoubleProperty parentHeightProperty, int columnSize) {
        super(); 
        this.tile = (new ReadOnlyObjectWrapper<>(tile));
        this.tile.addListener(this);
        this.columnSize = columnSize;
        this.parentHeightProperty = parentHeightProperty;
        this.getStylesheets().add(this.getClass().getResource("TilePane.css").toExternalForm()); 
        this.getStyleClass().add("tile");
        this.currentClass = "";
        this.drawThisTileAgainBecouseOfChangeOrCreation();
    }
    
    public Tile getTile() {
        return this.tile.getValue();
    }

    public void setTile(Tile tile) {
        this.tile = new ReadOnlyObjectWrapper<>(tile);
        drawThisTileAgainBecouseOfChangeOrCreation();
    }

    /**
     * Draws the tile again because of change or creation
     */
    public void drawThisTileAgainBecouseOfChangeOrCreation() {
        this.getChildren().removeAll(this.getChildren());
        this.getStyleClass().remove(currentClass);
        this.imageView = null;
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
        this.currentClass = tile.getValue().getTerrain().getName();
        this.getStyleClass().add(currentClass);
    }

    /**
     * Returns the image of on the tile
     * @return ImageView, the image on the tile or null if there is no image
     */
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void changed(ObservableValue<? extends Tile> observable, Tile oldValue, Tile newValue) {
        drawThisTileAgainBecouseOfChangeOrCreation();   
    }
}