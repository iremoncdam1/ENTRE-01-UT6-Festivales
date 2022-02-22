
import java.util.*;


/**
 * Esta clase guarda una agenda con los festivales programados
 * en una serie de meses
 *
 * La agenda guardalos festivales en una colecci�n map
 * La clave del map es el mes (un enumerado festivales.modelo.Mes)
 * Cada mes tiene asociados en una colecci�n ArrayList
 * los festivales  de ese mes
 *
 * Solo aparecen los meses que incluyen alg�n festival
 *
 * Las claves se recuperan en orden alfab�ico
 *
 */
public class AgendaFestivales {
    private TreeMap<Mes, ArrayList<Festival>> agenda;
    
    public AgendaFestivales() {
        this.agenda = new TreeMap<>();
    }

    /**
     * a�ade un nuevo festival a la agenda
     *
     * Si la clave (el mes en el que se celebra el festival)
     * no existe en la agenda se crear� una nueva entrada
     * con dicha clave y la colecci�n formada por ese �nico festival
     *
     * Si la clave (el mes) ya existe se a�ade el nuevo festival
     * a la lista de festivales que ya existe ese ms
     * insert�ndolo de forma que quede ordenado por nombre de festival.
     * Para este segundo caso usa el m�todo de ayuda
     * obtenerPosicionDeInsercion()
     *
     */
    public void addFestival(Festival festival) {
        ArrayList<Festival> festivales = new ArrayList<>();
        if (!agenda.containsKey(festival.getMes())) {
            festivales.add(festival);
            agenda.put(festival.getMes(), festivales);
        }

        if (!agenda.get(festival.getMes()).contains(festival)) {
            agenda.get(festival.getMes()).add(obtenerPosicionDeInsercion(agenda.get(festival.getMes()), festival), festival);
        }
    }

    /**
     *
     * @param festivales una lista de festivales
     * @param festival
     * @return la posici�n en la que deber�a ir el nuevo festival
     * de forma que la lista quedase ordenada por nombre
     */
    private int obtenerPosicionDeInsercion(ArrayList<Festival> festivales,
                                           Festival festival) {
        int pos = 0;
        for (int i = 0; i < festivales.size(); i++) {
            if (festivales.get(i).getNombre().compareTo(festival.getNombre()) < 0) {
                pos++;
            }
        }
        return pos;
        
    }

    /**
     * Representaci�n textual del festival
     * De forma eficiente
     *  Usa el conjunto de entradas par recorrer el map
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Mes, ArrayList<Festival>> entrada: agenda.entrySet()) {
            sb.append(entrada.getKey()).append("  (" + festivalesEnMes(entrada.getKey())).append(" festival/es)\n");
            Iterator<Festival> it = entrada.getValue().iterator();
            while (it.hasNext()) {
                sb.append(it.next()).append("\n");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     *
     * @param mes el mes a considerar
     * @return la cantidad de festivales que hay en ese mes
     * Si el mes no existe se devuelve -1
     */
    public int festivalesEnMes(Mes mes) {
        int suma = 0;
        if (agenda.containsKey(mes)) {
            for (int i = 0; i < agenda.get(mes).size(); i++) {
                suma++;
            }
        }
        return suma;
    }

    /**
     * Se trata de agrupar todos los festivales de la agenda
     * por estilo.
     * Cada estilo que aparece en la agenda tiene asociada una colecci�n
     * que es el conjunto de nombres de festivales que pertenecen a ese estilo
     * Importa el orden de los nombres en el conjunto
     *
     * Identifica el tipo exacto del valor de retorno
     */
    public TreeMap<Estilo,TreeSet<String>> festivalesPorEstilo() {
        TreeMap<Estilo,TreeSet<String>> porEstilos = new TreeMap<>();

        ArrayList<Festival> festivales = new ArrayList<>();                     // festivales = un ArrayList con todos los festivales
        for (Map.Entry<Mes,ArrayList<Festival>> entrada:agenda.entrySet()) {
            Iterator<Festival> it = entrada.getValue().iterator();
            while (it.hasNext()) {
                festivales.add(it.next());
            }
        }

        // Ahora quiero sacar el nombre y estilos de cada festival.
        String nombre = "";
        HashSet<Estilo> estilosDeCadaFestival = new HashSet<>();
        for (Festival festival:festivales) {

            estilosDeCadaFestival = festival.getEstilos();               // Sacamos los estilos de cada festival

            for (Estilo cadaEstilo: estilosDeCadaFestival) {
                nombre = festival.getNombre();                          //Sacamos el nombre de cada festival
                TreeSet<String> nombres = new TreeSet<>();
                nombres.add(nombre);
                if (!porEstilos.containsKey(cadaEstilo)) {
                    porEstilos.put(cadaEstilo, nombres);
                }
                    porEstilos.get(cadaEstilo).add(nombre);
            }
        }
        return porEstilos;
    }

    /**
     * Se cancelan todos los festivales organizados en alguno de los
     * lugares que indica el conjunto en el mes indicado. Los festivales
     * concluidos o que no empezados no se tienen en cuenta
     * Hay que borrarlos de la agenda
     * Si el mes no existe se devuelve -1
     *
     * Si al borrar de un mes los festivales el mes queda con 0 festivales
     * se borra la entrada completa del map
     */
    public int cancelarFestival(HashSet<String> lugares, Mes mes) {
       //TODO
        
        return 0;
    }
}
