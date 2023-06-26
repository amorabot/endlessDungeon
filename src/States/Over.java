package States;

public class Over extends State<EndlessDungeon>{

    private static final State<EndlessDungeon> INSTANCE = new Over();

    @Override
    public void execute(EndlessDungeon entity) {
        System.out.println("testing end of dungeon");
    }

    @Override
    public void enter(EndlessDungeon entity) {
        System.out.println("YOUR PATH ENDS HERE.");
    }

    @Override
    public void exit(EndlessDungeon entity) {

    }

    public static State<EndlessDungeon> getInstance(){
        return INSTANCE;
    }
}
