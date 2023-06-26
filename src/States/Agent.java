package States;

public abstract class Agent {

    State<? extends Agent> currentState;

    public abstract void update();
}
