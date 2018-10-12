package omegaRogue;

public class BeweglElement 
{
	int gewicht = 1;
	int x = 100;
	int y = 200;
	
	int vx = 2;
	int vy = -1;
	
	int breite = 0;
	int hoehe = 0;
	
	public void move()
	{
		int xa= 120;
		int ya= 240; 
		
		int xb=400;
		int yb=240;
		
		int xc=400;
		int yc=380;
		
		int xd=120;
		int yd=380;
		
		
		
		int xe= 680;
		int ye= 240; 
		
		int xf=960;
		int yf=240;
		
		int xg=960;
		int yg=380;
		
		int xh=680;
		int yh=380;
		
		
		
		int xi= 120;
		int yi= 520; 
		
		int xj=400;
		int yj=520;
		
		int xk=400;
		int yk=660;
		
		int xl=120;
		int yl=660;
		
		
		
		int xm=680;
		int ym=520;
		
		int xn= 960;
		int yn= 520; 
		
		int xo=960;
		int yo=660;
		
		int xp=680;
		int yp=660;
		
		
		
		x=x+vx;
		y=y+vy;
		
		if (((x>xa) && (x<xb)) && (y>yb) && (y<yb+14))
			vy=-vy;
		
		if (((x>xa) && (x<xb)) && (y<yc) && (y>yc-14))
			vy=-vy;
		
		if (((y>ya) && (y<yc)) && (x<xb) && (x>xb-14))
			vx=-vx;
		
		if (((y>ya) && (y<yc)) && (x>xa) && (x<xa+14))
			vx=-vx;
		
		
		
		if (((x>xe) && (x<xf)) && (y>yf) && (y<yf+14))
			vy=-vy;
		
		if (((x>xe) && (x<xf)) && (y<yg) && (y>yg-14))
			vy=-vy;
		
		if (((y>ye) && (y<yg)) && (x<xf) && (x>xf-14))
			vx=-vx;
		
		if (((y>ye) && (y<yg)) && (x>xe) && (x<xe+14))
			vx=-vx;
		
		
		
		if (((x>xi) && (x<xj)) && (y>yj) && (y<yj+14))
			vy=-vy;
		
		if (((x>xi) && (x<xj)) && (y<yk) && (y>yk-14))
			vy=-vy;
		
		if (((y>yi) && (y<yk)) && (x<xj) && (x>xj-14))
			vx=-vx;
		
		if (((y>yi) && (y<yk)) && (x>xi) && (x<xi+14))
			vx=-vx;
		
		
		if (((x>xm) && (x<xn)) && (y>yn) && (y<yn+14))
			vy=-vy;
		
		if (((x>xm) && (x<xn)) && (y<yo) && (y>yo-14))
			vy=-vy;
		
		if (((y>ym) && (y<yo)) && (x<xn) && (x>xn-14))
			vx=-vx;
		
		if (((y>ym) && (y<yo)) && (x>xm) && (x<xm+14))
			vx=-vx;
		
		
		if (x<0)
		{
			vx=-vx;
		}
		
		if (x>breite)
		{
			vx = -vx;
		}
		
		if (y<0)
		{
			vy=-vy;
		}
		
		if (y>hoehe)
		{
			vy=-vy;
		}
		
	}
	
	public void setV(int bvx, int bvy)
	{
		vx = bvx;
		vy = bvy;
	}
	
	public void setBreiteHoehe(int bs_breite, int bs_hoehe )
	{
		breite = bs_breite;
		hoehe = bs_hoehe;
	}

}
