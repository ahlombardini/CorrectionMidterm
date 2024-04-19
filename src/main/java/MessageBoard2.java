import java.util.*;


public record MessageBoard2(List<String> messages, Map<PlayerColor,Integer> points) {
    public MessageBoard2 {
        for(int p: points.values()){
            if(p < 0){
                throw new IllegalArgumentException();
            }
        }
        messages = List.copyOf(messages);
        points = Map.copyOf(points);
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

        public void addMessage(String message, int points, Set<PlayerColor> player){
            if(points < 0){
                throw new IllegalArgumentException();
            }

            messages.add(message);

            for(PlayerColor p: player){
                map.put(p, map.getOrDefault(p,0) + points);
            }

        }

        public MessageBoard2 build(){
            return new MessageBoard2(messages, map);
        }
    }


}
