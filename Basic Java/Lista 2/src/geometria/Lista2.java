/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geometria;

/**
 *
 * @author jacek
 */
class Wektor{
    final double x,y;
    public Wektor(double x, double y){
        this.x = x;
        this.y = y;
    }
}

class Prosta{
    final double a,b,c;
    public Prosta(double a, double b, double c) {
        if(a == 0 && b == 0){
            System.err.println("Nieprawidlowe dane \n");
            System.exit(1);
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }
    public Prosta przesuń(Wektor w){
        double nc = -c/b;
        nc += w.y;
        nc += (-w.x*a);
        nc = -nc*b;
        return new Prosta(a,b,nc);
    }
}

class Punkt{
    private double x, y;
    public Punkt(double x, double y){
        this.x = x;
        this.y = y;
    }
    public Punkt(){
        this(0,0); //this.x = 0; this.y = 0;
    }
    public double X(){
        return x;
    }
    public double Y(){
        return y;
    }
    public void przesuń(Wektor w){
        x += w.x;
        y += w.y;
    }
    public void obróć(Punkt p, double kąt){
        double nx = x - p.x;
        double ny = y - p.y;
        double rx = nx*Math.cos(Math.toRadians(kąt)) - ny*Math.sin(Math.toRadians(kąt));
        double ry = nx*Math.sin(Math.toRadians(kąt)) + ny*Math.cos(Math.toRadians(kąt));
        x = rx+p.x;
        y = ry+p.y;
    }
    public void odbij(Prosta p){
        if(p.a == 0){
            double nc = -p.c/p.b;
            y = 2*nc -y;
        }
        else if(p.b == 0){
            double nc = -p.c/p.a;
            x = 2*nc - x;         
        }
        else{
            double na = -p.a / p.b;
            double nc = -p.c / p.b;
            double mv = -nc / na;
            x = x - mv;
            double d = 1+na*na;
            double nx = ((1-na*na)/d)* x + (2*na/d)*y;
            double ny = (2*na/d)* x + ((1-na*na)/d)*y;
            x = nx + mv;
            y = ny;
        }
    }
}

class Odcinek{
    private Punkt p1,p2;
    public Odcinek(Punkt p1, Punkt p2) throws Exception{
        if(p1.X() == p2.X() && p1.Y() == p2.Y())
            throw new Exception("Nieprawidlowe dane dla odcinka.");
        this.p1 = p1;
        this.p2 = p2;
    }
    public double X1(){
        return p1.X();
    }
    public double Y1(){
        return p1.Y();
    }
    public double X2(){
        return p2.X();
    }
    public double Y2(){
        return p2.Y();
    }
    public void przesuń(Wektor w){
        p1.przesuń(w);
        p2.przesuń(w);
    }
    public void obróć(Punkt p, double kąt){
        p1.obróć(p, kąt);
        p2.obróć(p, kąt);
    }
    public void odbij(Prosta p){
        p1.odbij(p);
        p2.odbij(p);
    }
}

class Trojkat{
    private Punkt p1,p2,p3;
    public Trojkat(Punkt p1, Punkt p2, Punkt p3) throws Exception {
        if((p1.X() == p2.X() && p1.Y() == p2.Y()) ||(p2.X() == p3.X() && p2.Y() == p3.Y()) || (p1.X() == p3.X() && p1.Y() == p3.Y()))
            throw new Exception("Nieprawidlowe dane dla trojkata.");
        if(p1.X() * (p2.Y() - p3.Y()) + p2.X() * (p3.Y() - p1.Y()) + p3.X() * (p1.Y() - p2.Y()) == 0)
            throw new Exception("Nieprawidlowe dane dla trojkata.");
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }
    public double X1(){
        return p1.X();
    }
    public double Y1(){
        return p1.Y();
    }
    public double X2(){
        return p2.X();
    }
    public double Y2(){
        return p2.Y();
    }
    public double X3(){
        return p3.X();
    }
    public double Y3(){
        return p3.Y();
    }
    public void przesuń(Wektor w){
        p1.przesuń(w);
        p2.przesuń(w);
        p3.przesuń(w);
    }
    public void obróć(Punkt p, double kąt){
        p1.obróć(p, kąt);
        p2.obróć(p, kąt);
        p3.obróć(p, kąt);
    }
    public void odbij(Prosta p){
        p1.odbij(p);
        p2.odbij(p);
        p3.odbij(p);
    }
}

public class Lista2 {
    public static boolean rownolegle(Prosta x, Prosta y){
       double a1 = -x.a / x.b;
       double a2 = -y.a / y.b;
       if(a1 == a2)
           return true;
       else
           return false;
    }
    public static boolean prostopadle(Prosta x, Prosta y){
        double a1 = -x.a / x.b;
        double a2 = -y.a / y.b;
        if(a1 * a2 == -1)
            return true;
        else
            return false;
    }
    public static double wspoliniowe(Prosta x, Prosta y){
        double a1 = -x.a / x.b;
        double a2 = -y.a / y.b;
        double c1 = -x.c / x.b;
        double c2 = -y.c / y.b;
        return (c2-c1)/(a1-a2);
    }

    public static void main(String[] args) {
        Punkt a = new Punkt(2,2);
        Punkt a1 = new Punkt(2,0);
        Punkt a2 = new Punkt(1,1);
        //a.przesuń(new Wektor(1,1));
        //a.obróć(new Punkt(2,1), 180);
        //a.odbij(new Prosta(2,-1,0));
        a.odbij(new Prosta(1,-1,2));
        System.out.println(a.X() + " " + a.Y());
        try{
            Odcinek b = new Odcinek(a1,a2);
            //b.obróć(new Punkt(0,1),180);
            //b.przesuń(new Wektor(1,1));
            b.odbij(new Prosta(1,-1,2));
            System.out.println(b.X1() + " " + b.Y1() + " " + b.X2() + " " + b.Y2());
        }
        catch (Exception e){
            System.err.println(e);
            System.exit(1);
        }        
        try{
            Trojkat c = new Trojkat(new Punkt(0,1), new Punkt(5, 4), new Punkt(3,3));
            //c.obróć(new Punkt(0,1),180);
            //c.przesuń(new Wektor(1,1));
            c.odbij(new Prosta(1,-1,2));
            System.out.println(c.X1() + " " + c.Y1() + " " + c.X2() + " " + c.Y2()+ " " + c.X3() + " " + c.Y3());
        }
        catch (Exception e){
            System.err.println(e);
            System.exit(1);
        }
        Prosta p1 = new Prosta(3,3,2);
        Prosta p2 = new Prosta(1,1,0);
        System.out.println(rownolegle(p1,p2) + " " + prostopadle(new Prosta(1,-1,5),p1) + " " + wspoliniowe(p2,new Prosta(2,5,3)));
        
    }    
}
