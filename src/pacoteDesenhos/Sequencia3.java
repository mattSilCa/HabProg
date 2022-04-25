package pacoteDesenhos;

import java.util.ArrayList;
import java.util.Scanner;

public class Sequencia3 {
	private double x[];
	private double y[];

	// Construtor sem par�metros
	public Sequencia3() {
		System.out.println("DIgite n: ");
		Scanner in = new Scanner(System.in);
		// Previne contra erros de dados de entrada
		String aux = in.nextLine();
		int n = 3;

		// Tenta transformar em n�mero inteiro , se for poss�vel
		try {
			n = Integer.parseInt(aux);
		}

		catch (NumberFormatException e) {
			System.err.println(e + "\n");
			e.printStackTrace();
			System.out.println("Erro de leitura: " + e.getMessage());
			System.out.println("Assumindo n padr�o : " + n);

		}
		preencher(n);

	}

	// COnstrutor com par�metros
	public Sequencia3(int n, double d) {
		preencher(n);
	}

	public Sequencia3(ArrayList<Integer> cont) {
		preencherAL(cont);
	}

	// Novo m�todo preencher com ArrayList

	public void preencherAL(ArrayList<Integer> al) {
		// Criando n espa�os do tipo int para elem.
		try {
			x = new double[al.size()];
			y = new double[al.size()];
		}

		catch (NegativeArraySizeException e) {
			System.err.println(e + "\n");
			e.printStackTrace();
			int n = 3;
			System.out.println("Erro de tamanho de elemento: " + e.getMessage());
			System.out.println("Assumindo n padr�o: " + n);
			x = new double[n];
			y = new double[n];

		}

		// Criando um objeto da classe Scanner.
		Scanner in = new Scanner(System.in);
		String a;

		// Inicializando os componentes de elem.
		for (int i = 0; i< al.size(); i++) {

			// Previne contra erro de leitura e convers�o
			try {
				x[i] = al.get(i);
				// setElemX();
			}

			catch (NumberFormatException e) {
				System.err.println(e + "\n");
				e.printStackTrace();
				System.out.println("Erro de convers�o: " + e.getMessage());
				System.out.println("Assumindo x = 0");
				x[i] = 0;

			}


			// Previne contra erros de convers�o
			try {
				y[i] = 0;

			} catch (NumberFormatException e) {
				System.err.println(e + "\n");
				e.printStackTrace();
				System.out.println("Erro de convers�o: " + e.getMessage());
				System.out.println("Assumindo y = 0");
				y[i] = 0;
			}
		}
	}

	public void preencher(int n) {
		// Criando n espa�os do tipo int para elem.
		try {
			x = new double[n];
			y = new double[n];
		}

		catch (NegativeArraySizeException e) {
			System.err.println(e + "\n");
			e.printStackTrace();
			n = 3;
			System.out.println("Erro de tamanho de elemento: " + e.getMessage());
			System.out.println("Assumindo n padr�o: " + n);
			x = new double[n];
			y = new double[n];

		}

		// Criando um objeto da classe Scanner.
		Scanner in = new Scanner(System.in);
		String a;

		// Inicializando os componentes de elem.
		for (int i = 0; i < n; i++) {
			System.out.println("Digite x: ");
			a = in.nextLine();

			// Previne contra erro de leitura e convers�o
			try {
				x[i] = Integer.parseInt(a);
				// setElemX();
			}

			catch (NumberFormatException e) {
				System.err.println(e + "\n");
				e.printStackTrace();
				System.out.println("Erro de convers�o: " + e.getMessage());
				System.out.println("Assumindo x = 0");
				x[i] = 0;

			}

			System.out.println("Digite y: ");
			a = in.nextLine();

			// Previne contra erros de convers�o
			try {
				y[i] = Integer.parseInt(a);

			} catch (NumberFormatException e) {
				System.err.println(e + "\n");
				e.printStackTrace();
				System.out.println("Erro de convers�o: " + e.getMessage());
				System.out.println("Assumindo y = 0");
				y[i] = 0;
			}
		}
	}

	// M�todo para alterar um elemento de X.
	public void setElemX(int i, int aux) {
		// Previne contra erro de indices fora dos limites do vetor
		try {
			x[i] = aux;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println(e + "\n");
			e.printStackTrace();
			System.out.println("Indice fora dos limites: " + e.getMessage());

		}
	}

	// M�todo para retornar um elemento de X.
	public double getElemX(int i) {
		// Previne contra erro de �ndices fora dos limites do vetos.
		try {
			return x[i];

		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println(e + "\n");
			e.printStackTrace();
			System.out.println("�ndice fora dos limites: " + e.getMessage());
			return 0;
		}
	}

	// M�todo para alterar um elemento de Y.
	public void setElemY(int i, int aux) {
		// Previne contra erro de indices fora dos limites do vetor
		try {
			y[i] = aux;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println(e + "\n");
			e.printStackTrace();
			System.out.println("Indice fora dos limites: " + e.getMessage());

		}
	}

	// M�todo para retornar um elemento de Y.
	public double getElemY(int i) {
		// Previne contra erro de �ndices fora dos limites do vetos.
		try {
			return y[i];

		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println(e + "\n");
			e.printStackTrace();
			System.out.println("�ndice fora dos limites: " + e.getMessage());
			return 0;
		}
	}

	// M�todo para retornar o menor elemento de X.
	public double getXminElem() {
		// Menor valor provis�rio.
		double aux = x[0];

		for (int i = 0; i < x.length; i++) {
			if (x[i] < aux)
				aux = x[i];
		}

		return aux;
	}

	// M�todo para retornar o maior elemento de X.
	public double getXmaxElem() {
		// Maior valor provis�rio.
		double aux = x[0];

		for (int i = 0; i < x.length; i++) {
			if (x[i] > aux)
				aux = x[i];
		}

		return aux;
	}

	// M�todo para retornar o menor elemento de Y.
	public double getYminElem() {
		// Menor valor provis�rio.
		double aux = getElemY(0);

		for (int i = 0; i < y.length; i++) {
			if (getElemY(i) < aux)
				aux = getElemY(i);
		}

		return aux;
	}

	// M�todo para retornar o maior elemento de X.
	public double getYmaxElem() {
		// Maior valor provis�rio.
		double aux = y[0];

		for (int i = 0; i < y.length; i++) {
			if (y[i] > aux)
				aux = y[i];
		}

		return aux;
	}
	
	public void graf() {
		// Definindo a escala do fr�fico atrav�s de m�todos
		// que retornam os menores e maiores valores em x i y.
		double xmin = getXminElem();
		double xmax = getXmaxElem();
		double ymin = getYminElem();
		double ymax = getYmaxElem();
		StdDraw.setXscale(xmin - 1, xmax + 1);
		StdDraw.setYscale(ymin - 1, ymax + 1);

		StdDraw.clear();

		// La�o para desenhar
		for (int i = 1; i < x.length; i++) {
			StdDraw.setPenColor(StdDraw.BLUE);
			StdDraw.filledCircle(x[i], y[i], 0.05);
			StdDraw.setPenColor(StdDraw.RED);
			StdDraw.line(x[i - 1], y[i - 1], x[i], y[i]);
		}
	}
	
	
	/*
	public static void main(String[] arg) {
		new Sequencia3().graf();
	}
	*/
}
