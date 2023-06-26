package States;

public abstract class State <T extends Agent> {

    public abstract void execute(T entity);
    public abstract void enter(T entity);
    public abstract void exit(T entity);
}
