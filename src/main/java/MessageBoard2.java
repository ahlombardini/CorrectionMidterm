import java.util.*;


public record MessageBoard2(List<String> messages, Map<PlayerColor,Integer> points) {
    public MessageBoard2 {
        for(int p: points.values()){
            if(p <= 0){
                throw new IllegalArgumentException();
            }
        }
        messages = List.copyOf(messages);
        points = Map.copyOf(points);
    }

    public Set<PlayerColor> leaders(){
        Map<Integer,Set<PlayerColor>> m = new HashMap<>();
        int max = 1;
        for(Map.Entry<PlayerColor,Integer> entry: points.entrySet()) {
            int v = entry.getValue();
            PlayerColor k = entry.getKey();
            Set<PlayerColor> s = m.getOrDefault(v, new HashSet<>());
            s.add(k);
            m.put(v,s);
            max = Math.max(v, max);
        }

        return m.get(max);
    }

    public static final class Builder{
        private final List<String> messages;
        private final Map<PlayerColor,Integer> map ;

        public Builder(MessageBoard2 mB){
            this.messages = new LinkedList<>(mB.messages);
            this.map = new HashMap<>(mB.points);
        }

        public void addMessage(String message){
            messages.add(message);
        }

        public void addMessage(String message, int points, Set<PlayerColor> scorers){
            if(points <= 0 || scorers.isEmpty()){
                throw new IllegalArgumentException();
            }

            messages.add(message);

            for(PlayerColor p : scorers){
                map.put(p, map.getOrDefault(p,0) + points);
            }

        }

        public MessageBoard2 build(){
            return new MessageBoard2(messages, map);
        }
    }


}
