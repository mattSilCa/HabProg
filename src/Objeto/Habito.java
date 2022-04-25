package Objeto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Habito {

	// Atributos
	private String nome;
	private int acomp = 0;
	int[] tHab = { 0, 0 };
	Date dataIni;
	ArrayList<String> descricao = new ArrayList<String>();
	ArrayList<Integer> cont = new ArrayList<Integer>();
	Calendar calendar, dataAtividade;
	double barra;
	public double progresso;

	// Construtores
	public Habito() {
		this("");
	}

	public Habito(String nome) {
		calendar = Calendar.getInstance();
		this.nome = nome;
	}

	public Habito(String nome, String splitD, String splitTHab, int dia, int mes, int ano, String cont) {
		this.nome = nome;
		String[] s = splitD.split(cS());
		int i = 0;
		do {
			descricao.add(s[i]);
			i++;
		} while (i < s.length);
		calendar = new GregorianCalendar(ano, (mes - 1), dia);

		try {
			if (cont.equals("")) {

			} else {
				String[] s2 = cont.split(cS());
				i = 0;
				do {
					this.cont.add(Integer.parseInt(s2[i]));
					i++;
				} while (i < s2.length);
			}
		} catch (Exception eCont) {

		}

		calendar = new GregorianCalendar(ano, mes, dia);

		String t[] = splitTHab.split(cS());
		i = 0;
		do {
			tHab[i] = Integer.parseInt(t[i]);
			i++;
		} while (i < t.length);

	}

	// Métodos

	public ArrayList<Integer> getAL() {
		return cont;
	}

	public String getName() {
		return this.nome;
	}

	public int calculaNivel() {
		double[] y = new double[2];
		double tempo;
		int x = 0;
		tempo = tHab[0] + tHab[1] / 60.0;

		do {

			y[0] = 0.1 * x * x;
			y[1] = 0.1 * (x + 1) * (x + 1);

			if ((tempo <= y[1] && tempo >= y[0]) || tempo == 0) {
				this.barra = y[1] - y[0];
				this.progresso = (tempo - y[0]) * 100 / barra;
				break;

			}
			x++;

		} while (true);
		return x;

	}

	public String showHab() {
		// Formatar dados de tempo
		String s = "\n\tHábito: " + nome + "\n\n\tIniciado em " + calendar.get(calendar.DATE) + "/"
				+ (calendar.get(calendar.MONTH) + 1) + "/" + calendar.get(calendar.YEAR) + "\n\n\tTempo dedicado: "
				+ tHab[0] + ":" + tHab[1];
		s += "\n\n\tNível: " + calculaNivel() + "/100";
		s += "\n\t------------------------------------------";
		return s;
	}

	public void makeDesc(String rel, int hour, int min) {
		String s;
		tHab[0] += hour;
		tHab[1] += min;
		if (tHab[1] >= 60) {
			tHab[0]++;
			tHab[1] -= 60;
		}
		
		dataAtividade = Calendar.getInstance();
		int[] year = new int[2];
		int[] day = new int[2];
		
		day[1] = calendar.get(Calendar.DAY_OF_YEAR);
		year[1] = calendar.get(calendar.YEAR);
		day[0] = dataAtividade.get(Calendar.DAY_OF_YEAR);
		year[0] = dataAtividade.get(Calendar.YEAR);
		

		int a = 123;

		if (year[0] == year[1])
			a = day[0] - day[1];
		else if (year[0] > (year[1]))
			a = (year[0] - year[1]) * 365 - day[1] + day[0];

		cont.add(a);

		s = "data :" + dataAtividade.get(Calendar.DATE) + "/" + (dataAtividade.get(Calendar.MONTH) + 1) + "/"
				+ dataAtividade.get(Calendar.YEAR) + "\nRelatório: " + rel + "\nTempo dedicado: " + hour + ":" + min;

		descricao.add(s);

	}

	public String showDesc() {
		String s = "";
		try {
			if (descricao.get(0).equals("")) {
				descricao.remove(0);
			} else {
				for (int i = 0; i < descricao.size(); i++) {
					s += "\nEntrada " + (i + 1) + "\n" + descricao.get(i) + "\n" + "-------------------------------";
				}
			}
		} catch (Exception e) {

		}
		return s;

	}

	public void delDesc(int i) {
		descricao.remove(i);
	}

	public String c() {
		return "/ - /";
	}

	public String cS() {
		return "/ _ /";
	}

	public String registrar() {
		// String nome, String splitD, int splitTHab, int dia, int mes, int ano

		String s = nome + c();
		for (int i = 0; i < descricao.size(); i++) {
			s += descricao.get(i);
			if (i < (descricao.size() - 1))
				s += cS();
		}

		s += c() + tHab[0] + cS() + tHab[1] + c() + calendar.get(calendar.DATE) + c()
				+ (calendar.get(calendar.MONTH)) + c() + calendar.get(calendar.YEAR) + c();

		for (int i = 0; i < cont.size(); i++) {
			s += cont.get(i);
			if (i < (cont.size() - 1))
				s += cS();
		}

		s += "Mud4r";
		return s;

	}
}
