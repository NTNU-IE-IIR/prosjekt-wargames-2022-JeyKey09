package no.ntnu.mathijoh.wargame.fxmodels;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableObjectValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import no.ntnu.mathijoh.wargame.models.map.Tile;

/**
 * A pane that represents a tile on the map.
 * It should change when the tile changes, but it is not yet fully implemented.
 * 
 * @author Mathias J. Kirkeby
 * @version 1.0
 */
public class TilePane extends BorderPane implements ChangeListener<Tile> {

    private ObservableObjectValue<Tile> tile;
    private ImageView imageView;
    private ReadOnlyDoubleProperty parentHeightProperty;
    private int columnSize;
    private String terrainStyleClass;

    /**
     * Constructor of the tile pane.
     * 
     * @param tile The tile that the pane should represent.
     */
    public TilePane(Tile tile) {
        super();
        setTile(tile);
        this.tile.addListener(this);
        this.getStylesheets().add(this.getClass().getResource("TilePane.css").toExternalForm());
        this.getStyleClass().add("tile");
        this.drawTile();
    }

    /**
     * Constructor of the tile pane.
     * It lets the tile pane know about the parent height property for scaling
     * purposes within the window
     * 
     * @param tile                 The tile that the pane should represent.
     * @param parentHeightProperty The parent height property.
     * @param columnSize           The amount of coulumns within a map.
     */
    public TilePane(Tile tile, ReadOnlyDoubleProperty parentHeightProperty, int columnSize) {
        super();
        if(columnSize < 0){
            throw new IllegalArgumentException("Column size must be greater than 0");
        }
        if(parentHeightProperty == null){
            throw new IllegalArgumentException("Parent height property must not be null");
        }
        this.columnSize = columnSize;
        this.parentHeightProperty = parentHeightProperty;
        this.getStylesheets().add(this.getClass().getResource("TilePane.css").toExternalForm());
        this.getStyleClass().add("tile");
        this.terrainStyleClass = "";
        setTile(tile);
    }

    /**
     * Gets the tile that the pane represents.
     * 
     * @return The tile that the pane represents.
     */
    public Tile getTile() {
        return this.tile.getValue();
    }

    /**
     * Sets the tile that the pane should represent and redraws it.
     * 
     * @param tile the tile that the pane should represent.
     * @throws IllegalArgumentException if the tile is null.
     */
    public void setTile(Tile tile) throws IllegalArgumentException {
        if (tile == null) {
            throw new IllegalArgumentException("Tile cannot be null");
        }
        if(this.tile != null){
            this.tile.removeListener(this);
        }
        this.tile = new ReadOnlyObjectWrapper<>(tile);
        this.tile.addListener(this);
        drawTile();
    }

    /**
     * Draws the tile.
     */
    public void drawTile() {
        this.getChildren().removeAll(this.getChildren());
        this.getStyleClass().remove(terrainStyleClass);
        this.imageView = null;
        if (getTile().getToken() != null) {
            imageView = new ImageView();
            imageView.setImage(new Image(TilePane.class
                    .getResource("images/tokens/" + getTile().getToken().getUnit().getClass().getSimpleName() + ".png")
                    .toExternalForm()));
            imageView.setPreserveRatio(true);
            BorderPane container = new BorderPane(imageView);
            container.getStyleClass().add("token-" + tile.getValue().getToken().getColor().toLowerCase());
            imageView.fitHeightProperty().bind(this.parentHeightProperty.divide(columnSize * 6 / 4));
            container.maxHeightProperty().bind(imageView.fitHeightProperty());
            container.maxWidthProperty().bind(imageView.fitWidthProperty());
            this.setCenter(container);
        }
        this.terrainStyleClass = tile.getValue().getTerrain().getName();
        this.getStyleClass().add(terrainStyleClass);
    }

    /**
     * Returns the image of on the tile
     * 
     * @return ImageView, the image of the unit on the tile or null if there is no
     *         unit on the tile
     */
    public ImageView getImageView() {
        return imageView;
    }

    @Override
    public void changed(ObservableValue<? extends Tile> observable, Tile oldValue, Tile newValue) {
        drawTile();
    }
}