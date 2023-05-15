import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class parametros {
	
  

	public static void main(String[] args) {
		// Inicializar Scanner para leer la entrada del usuario
		Scanner scanner = new Scanner(System.in);

		// Nombre del archivo XML con los parámetros del juego
		String fileName = ".\\src\\parametros.xml";

		// Crear objeto DocumentBuilderFactory para obtener un objeto DocumentBuilder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String infecciones1="";
		String infecciones2="";
		String ciudadesActivasDerrota="";
		String numbrotesderrota="";
		String porcentajeVacuna ="";
		
		

		try {
			// Crear objeto DocumentBuilder a partir del objeto DocumentBuilderFactory
			DocumentBuilder builder = factory.newDocumentBuilder();
			// Leer el archivo XML y parsearlo en un objeto Document
			Document document = builder.parse(new File(fileName));
			// Normalizar el Document para asegurar la coherencia de los datos
			document.getDocumentElement().normalize();
			
			NodeList nodeList = document.getElementsByTagName("parametros");
			Element parametros = (Element) nodeList.item(0);
			if (nodeList.getLength() > 0) {
			infecciones1= parametros.getElementsByTagName("numCiudadesInfectadasInicio").item(0).getTextContent();
			infecciones2= parametros.getElementsByTagName("numCuidadesInfectadasRonda").item(0).getTextContent();
			ciudadesActivasDerrota=parametros.getElementsByTagName("numEnfermedadesActivasDerrota").item(0).getTextContent();
			numbrotesderrota=parametros.getElementsByTagName("numBrotesDerrota").item(0).getTextContent();
			porcentajeVacuna = parametros.getElementsByTagName("porcentajeDesarrolloVacuna").item(0).getTextContent();
			
			//asignacion de valores

		    
			
			

			
			
			
			}
			
			// Variable booleana para controlar el flujo del programa
			boolean salir = false;
			while (!salir) {
				// Mostrar el menú de opciones
				System.out.println("\nMENU");
				System.out.println("1. Mostrar parametros");
				System.out.println("2. Modificar parametros");
				System.out.println("3. Salir");
				System.out.print("Elija una opcion: ");

				// Leer la opción seleccionada por el usuario
				int opcion = scanner.nextInt();

				switch (opcion) {
				// Mostrar los parámetros actuales del juego
				case 1:
					mostrarParametros(document);
					break;
				// Modificar los parámetros del juego
				case 2:
					modificarParametros(document, fileName);
					break;
				// Salir del programa
				case 3:
					salir = true;
					break;
				// Opción no válida
				default:
					System.out.println("Opcion no valida. Intente de nuevo.");
					break;
				}
			}

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// Manejar las excepciones que puedan surgir durante la ejecución del programa
			e.printStackTrace();
		}
		int	InfeccionesInicio = Integer.parseInt(infecciones1);
		int InfeccionesRondas = Integer.parseInt(infecciones2);
		int EnfermedadesDerrota=Integer.parseInt(ciudadesActivasDerrota);
	int	BrotesDerrota =Integer.parseInt(numbrotesderrota);
	   int PorcentajeVacuna=Integer.parseInt(porcentajeVacuna);
		
	
	    
	}

	
	

	
	// Mostrar los parámetros actuales del juego
	private static void mostrarParametros(Document document) {
		// Obtener una lista de nodos con el tag "parametros"
		NodeList nodeList = document.getElementsByTagName("parametros");

		if (nodeList.getLength() > 0) {
			// Obtener el primer elemento "parametros"
			Element parametros = (Element) nodeList.item(0);
			System.out.println("\nPARAMETROS\n");
			System.out.println("Numero de ciudades infectadas al inicio: "
					+ parametros.getElementsByTagName("numCiudadesInfectadasInicio").item(0).getTextContent());
			System.out.println("Numero de ciudades infectadas por ronda: "
					+ parametros.getElementsByTagName("numCuidadesInfectadasRonda").item(0).getTextContent());
			System.out.println("Numero de enfermedades activas para perder el juego: "
					+ parametros.getElementsByTagName("numEnfermedadesActivasDerrota").item(0).getTextContent());
			System.out.println("Numero de brotes para perder el juego: "
					+ parametros.getElementsByTagName("numBrotesDerrota").item(0).getTextContent());
			System.out.println("El porcentaje de desarrollo de las vacunas: "
					+ parametros.getElementsByTagName("porcentajeDesarrolloVacuna").item(0).getTextContent());
			System.out.println("-------------------------------------------------------------------------");
		} else {
			System.out.println("No se encontraron parametros en el archivo.");
		}
	}

//modificar los parametros
	private static void modificarParametros(Document document, String fileName) {
		NodeList nodeList = document.getElementsByTagName("parametros");
		int opcion = 0;
		boolean repetir = true;
		Scanner scanner = new Scanner(System.in);
		Scanner scanner2 = new Scanner(System.in);

		repetir = true;

		while (repetir == true) {
			System.out.println("-------------------------------------------------------------------------");
			System.out.println("Elige el parametro que quieras modificar");
			System.out.println("1: Numero de ciudades infectadas al inicio");
			System.out.println("2: Numero de ciudades infectadas por ronda");
			System.out.println("3: Numero de enfermedades activas para perder el juego");
			System.out.println("4: Numero de brotes para perder el juego");
			System.out.println("5: El porcentaje de desarrollo de las vacunas");
			System.out.println("6: Dejar de modificar parametros");
			opcion = scanner2.nextInt();
			System.out.println("-------------------------------------------------------------------------");
			if (opcion == 1) {
				Element parametros = (Element) nodeList.item(0);
				System.out.print("Numero de ciudades infectadas al inicio (actual: "
						+ parametros.getElementsByTagName("numCiudadesInfectadasInicio").item(0).getTextContent()
						+ "): ");
				String numCiudadesInfectadasInicio = scanner.nextLine();
				if (numCiudadesInfectadasInicio.equals("")) {

				} else {
					parametros.getElementsByTagName("numCiudadesInfectadasInicio").item(0)
							.setTextContent(numCiudadesInfectadasInicio);
					guardarCambios(document, fileName);
					System.out.println("\nCambios guardados con exito.");
				}
			}
			////////////////////////////////////////////////////////////////////////////////////////
			else if (opcion == 2) {
				Element parametros = (Element) nodeList.item(0);
				System.out.print("Numero de ciudades infectadas por ronda (actual: "
						+ parametros.getElementsByTagName("numCuidadesInfectadasRonda").item(0).getTextContent()
						+ "): ");
				String numCuidadesInfectadasRonda = scanner.nextLine();
				if (numCuidadesInfectadasRonda.equals("")) {

				} else {
					parametros.getElementsByTagName("numCuidadesInfectadasRonda").item(0)
							.setTextContent(numCuidadesInfectadasRonda);
					guardarCambios(document, fileName);
					System.out.println("\nCambios guardados con exito.");
				}
			}
			////////////////////////////////////////////////////////////////////////////////////////
			else if (opcion == 3) {
				Element parametros = (Element) nodeList.item(0);
				System.out.print("Numero de enfermedades activas para perder el juego (actual: "
						+ parametros.getElementsByTagName("numEnfermedadesActivasDerrota").item(0).getTextContent()
						+ "): ");
				String numEnfermedadesActivasDerrota = scanner.nextLine();
				if (numEnfermedadesActivasDerrota.equals("")) {

				} else {
					parametros.getElementsByTagName("numEnfermedadesActivasDerrota").item(0)
							.setTextContent(numEnfermedadesActivasDerrota);

					guardarCambios(document, fileName);
					System.out.println("\nCambios guardados con exito.");
				}
			}
			////////////////////////////
			else if (opcion == 4) {
				Element parametros = (Element) nodeList.item(0);
				System.out.print("Numero de brotes para perder el juego (actual: "
						+ parametros.getElementsByTagName("numBrotesDerrota").item(0).getTextContent() + "): ");
				String numBrotesDerrota = scanner.nextLine();

				if (numBrotesDerrota.equals("")) {

				} else {

					parametros.getElementsByTagName("numBrotesDerrota").item(0).setTextContent(numBrotesDerrota);
					guardarCambios(document, fileName);
					System.out.println("\nCambios guardados con exito.");
				}

			}
			else if (opcion == 5) {
				Element parametros = (Element) nodeList.item(0);
				System.out.print("El porcentaje de desarrollo de las vacunas (actual: "
						+ parametros.getElementsByTagName("porcentajeDesarrolloVacuna").item(0).getTextContent() + "): ");
				String porcentajeDesarrolloVacuna = scanner.nextLine();

				if (porcentajeDesarrolloVacuna.equals("")) {

				} else {

					parametros.getElementsByTagName("porcentajeDesarrolloVacuna").item(0).setTextContent(porcentajeDesarrolloVacuna);
					guardarCambios(document, fileName);
					System.out.println("\nCambios guardados con exito.");
				}

			}

			else if (opcion == 6) {
				repetir = false;
			}

		}

	}

// guardar los cambios
	private static void guardarCambios(Document document, String fileName) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			DOMSource source = new DOMSource(document);
			StreamResult result = new StreamResult(new FileWriter(fileName));

			transformer.transform(source, result);
		} catch (IOException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	



}

// Mohamed Oundouh - David Mir 