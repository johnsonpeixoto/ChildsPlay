import javafx.scene.shape.Path;

public interface CallBacks {
    void updateTable();

    void updateLog(String oldLog);

    void updateCesta();

    void updatePath(Kid kid, Path path);

    void setPathBrincando(Kid kid);

    void setPathQuieta(Kid kid);

    void setPathBloqueada(Kid kid);

    void setPathBloqueadaComBola(Kid kid);

}
