package TD.Event;

import TD.TD;
import TD.UI.Ground;
import TD.UI.Interface;
import TD.UI.Menu;
import TD.UI.Score;
import TD.UI.Tower;

public class Event
{
    public static boolean inRect(int mx, int my, int x1, int y1, int x2, int y2)//True si (posX, posY) est dans le carré définie par x1,y1,x2,y2
    {
        return mx >= x1 && mx <= x2 && my >= y1 && my <= y2;
    }
    
    public static boolean inCircle(int mx, int my, int x, int y, int r)         //Distance 2 entre les 2 points
    {
        int vx = Math.abs(mx - x);
        int vy = Math.abs(my - y);
        return Math.sqrt(vx * vx + vy * vy) <= r;
    }
    
    public static boolean inBuildableCase(int mx, int my)
    {
        int x = mx / Ground.W;
        int y = my / Ground.W;
        boolean cond1 = y < Ground.tabMap[Ground.map].length;
        boolean cond2 = x < Ground.tabMap[Ground.map][0].length;
        if(cond1 && cond2 && Interface.selec != 0)
        {
            int c = Ground.tabMap[Ground.map][y][x];
            for(Tower t : Tower.towers)
                if(x == t.xc && y == t.yc)
                    return false;
            return Ground.tabProp[c][1] == 1;
        }
        return false;
    }
    
    public static void mousePressed(int mx, int my)
    {
        switch(TD.choice)
        {
            case 0:
                if(inRect(mx, my, 530, 300, 730, 380))                          //Bouton jouer
                {
                    TD.gameInit();
                    TD.choice = 1;
                }
                else if(inRect(mx, my, 520, 490, 720, 530))                     //Bouton score
                    TD.choice = 3;
                else if(inRect(mx, my, 560, 650, 700, 700))                     //Bouton quitter
                    TD.choice = 4;
                break;
            case 1:                                                             
                if(inRect(mx, my, 1086, 60, 1155, 131))                         ////
                    Interface.selec = 1;                                        //
                else if(inRect(mx, my, 1205, 59, 1274, 130))                    //
                    Interface.selec = 2;                                        //
                else if(inRect(mx, my, 1145, 169, 1214, 240))                   // Clics dans l'interface / séléction d'une tourelle
                    Interface.selec = 3;                                        //
                else if(inRect(mx, my, 1089, 282, 1158, 353))                   //
                    Interface.selec = 4;                                        //
                else if(inRect(mx, my, 1204, 282, 1273, 353))                   //
                    Interface.selec = 5;                                        ////
                else if(inBuildableCase(mx, my))
                {
                    Tower.towers.add(new Tower(Interface.selec - 1, mx / Ground.W, my / Ground.W));
                    Tower.selec = Tower.towers.size()-1;
                }
                //else if()
                //    Tower.selec
                else
                    Interface.selec = 0;                                        //Déselection
                break;
            case 3:
                if(inRect(mx, my, 1000, 620, 1250, 680))
                {
                    Menu.colS = TD.COLOR1;
                    TD.choice = 0;
                }
                break;

        }
    }

    public static void mouseMoved(int mx, int my)
    {
        switch(TD.choice)
        {
            case 0:
                if(inRect(mx, my, 530, 300, 730, 380))                          //Bouton jouer
                    Menu.colJ = TD.COLOR2;
                else if(inRect(mx, my, 520, 490, 720, 530))                     //Bouton score
                    Menu.colS = TD.COLOR2;
                else if(inRect(mx, my, 560, 650, 700, 700))                     //Bouton quitter
                    Menu.colQ = TD.COLOR2;
                else
                {
                    Menu.colJ = TD.COLOR1;
                    Menu.colS = TD.COLOR1;
                    Menu.colQ = TD.COLOR1;
                }
                break;
            case 1:
                Interface.mx = mx;                                                      //Transmission de la position de la souris à Interface.
                Interface.my = my;
                break;
            case 2:
                break;
            case 3:
                if(inRect(mx, my, 1000, 620, 1250, 680))
                    Score.colR = TD.COLOR2;
                else
                    Score.colR = TD.COLOR1;
                break;
        }
    }
}