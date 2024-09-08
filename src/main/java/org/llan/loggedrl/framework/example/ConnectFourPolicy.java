package org.llan.loggedrl.framework.example;

import org.llan.loggedrl.framework.policies.EpsilonGreedy;
import org.llan.loggedrl.framework.save.DataSaver;
import org.llan.loggedrl.framework.util.Matrix;

public class ConnectFourPolicy extends EpsilonGreedy {

    private int _playerId;

    public ConnectFourPolicy(double epsilon, Player player, ConnectFour game) {
        super(epsilon, player.getId() - 1);
        _playerId = player.getId();
        setValueFunction(new ConnectFourFunction(player, game));
        setEpsilonDecay(AIConstants.EPSILON_DECAY);
        loadParams();
    }

    @Override
    public void saveParams() {
        Matrix matrix = new Matrix(1, 1);
        matrix.set(0, 0, getEpsilon());
        DataSaver.saveMatrixToFile(matrix, "epilson_" + _playerId + ".txt");
    }

    @Override
    public void loadParams() {
        Matrix matrix = DataSaver.loadMatrixFromFile("epilson_" + _playerId + ".txt");
        if(matrix != null) {
            setEpsilon(matrix.get(0, 0));
        }
    }
}
