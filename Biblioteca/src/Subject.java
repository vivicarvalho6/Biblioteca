import java.util.ArrayList;

public abstract class Subject
{
    private ArrayList<Observer> observers=new ArrayList<Observer>();
    
    public void registerObserver(Observer o){
        observers.add(o);
    }
    
    public void removeObserver(Observer o){
        observers.remove(o);
    }
    
    public void notifyObservers(){
        for (Observer o: observers){
            o.atualizar(this);
        }
    }
}
