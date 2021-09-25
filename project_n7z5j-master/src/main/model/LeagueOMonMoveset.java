package model;

//  Interface, every LeagueOMon subclass has these methods. These methods are unique to every leagueOMon.

public interface LeagueOMonMoveset {
    void specialMove(LeagueOMon enemy);

    void basicAttack(LeagueOMon enemy);

    void secondMove(LeagueOMon enemy);

    void thirdMove(LeagueOMon enemy);

    void passive(LeagueOMon enemy);
}
