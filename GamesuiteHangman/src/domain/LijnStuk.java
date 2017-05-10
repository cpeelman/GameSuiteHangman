package domain;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class LijnStuk extends Vorm implements Drawable {
	private Punt StartPunt;
	private Punt EindPunt;

	public LijnStuk(Punt startPunt, Punt eindPunt) {
		if (startPunt == null || eindPunt == null) {
			throw new DomainException("Startpunt en eindpunt mogen niet null zijn.");
		}
		setStartEnEindPunt(startPunt, eindPunt);
	}

	public Punt getStartPunt() {
		return StartPunt;
	}

	public Punt getEindPunt() {
		return EindPunt;
	}

	public void setStartEnEindPunt(Punt startPunt, Punt eindPunt) {
		this.StartPunt = startPunt;
		this.EindPunt = eindPunt;
	}

	@Override
	public boolean equals(Object object) {
		if (object == null) {
			return false;
		}
		if(!(object instanceof LijnStuk)){
			return false;
		}
		LijnStuk lijnstuk = (LijnStuk) object;
		if(lijnstuk.getStartPunt().equals(this.getStartPunt())
				&& lijnstuk.getEindPunt().equals(this.getEindPunt())) {
			return true;
		}
		if(lijnstuk.getStartPunt().equals(this.getEindPunt())
				&& lijnstuk.getEindPunt().equals(this.getStartPunt())) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Lijn: startpunt: " + getStartPunt().toString() + " - eindpunt: " + getEindPunt().toString() + " - " + getOmhullende().toString() ;
	}

	@Override
	public Omhullende getOmhullende() {
		int hoogte = 0;
		int minY = 0;
		int breedte = 0;
		int minX = 0;
		
		Omhullende omhullende = new Omhullende(new Punt(minX, minY), breedte, hoogte);
		if (getStartPunt().getY() == getEindPunt().getY() && getStartPunt().getX() == getEindPunt().getX()) {
			return null;
		}
		
		if (getStartPunt().getY() < getEindPunt().getY()) {
			minY = getStartPunt().getY();
			hoogte = getEindPunt().getY() - getStartPunt().getY();
		} else {
			minY = getEindPunt().getY();
			hoogte = getStartPunt().getY() - getEindPunt().getY();
		}
		
		if (getStartPunt().getX() < getEindPunt().getX()) {
			minX = getStartPunt().getX();
			breedte = getEindPunt().getX() - getStartPunt().getX();
		} else {
			minX = getEindPunt().getX();
			breedte = getStartPunt().getX() - getEindPunt().getX();
		}

		
		return omhullende;
	}

	@Override
	public void teken(Graphics graphics) {
		Graphics2D graphics2D = (Graphics2D) graphics;
		graphics2D.setStroke(new BasicStroke(5));
		
		LijnStuk lijnstuk = new LijnStuk(this.getStartPunt(),this.getEindPunt());
		graphics.drawLine(lijnstuk.getStartPunt().getX(), lijnstuk.getStartPunt().getY(), lijnstuk.getEindPunt().getX(), lijnstuk.getEindPunt().getY());
		
	}

}
