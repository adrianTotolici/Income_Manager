package eu.IncomeManager.communication;

import com.google.code.gsonrmi.Parameter;
import com.google.code.gsonrmi.RpcError;
import com.google.code.gsonrmi.annotations.RMI;
import com.google.code.gsonrmi.serializer.ExceptionSerializer;
import com.google.code.gsonrmi.serializer.ParameterSerializer;
import com.google.code.gsonrmi.transport.Route;
import com.google.code.gsonrmi.transport.Transport;
import com.google.code.gsonrmi.transport.rmi.Call;
import com.google.code.gsonrmi.transport.rmi.RmiService;
import com.google.code.gsonrmi.transport.tcp.TcpProxy;
import com.google.code.gsonrmi.transport.tcp.TcpProxyFactory;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.IncomeManager.Logic.DataBaseLogic;
import eu.IncomeManager.Logic.Logic;
import eu.IncomeManager.Utils.Constante;
import eu.IncomeManager.communication.beans.ProductBeans;
import eu.IncomeManager.dataBase.Depozit;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Server {
    private static Server instance;
    private Transport t;
    private TcpProxy tcpProxy;

    private Server() {}

    private void startServer(){
        try{
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Exception.class, new ExceptionSerializer())
                    .registerTypeAdapter(Parameter.class, new ParameterSerializer()).create();
            t = new Transport();


            InetSocketAddress inetSocketAddress = new InetSocketAddress(Constante.soketDesktop);
            List<InetSocketAddress> listeningAddresses = Arrays.asList(inetSocketAddress);

            tcpProxy = TcpProxyFactory.reflectTcpProxy(TcpProxy.class, listeningAddresses, t, gson, null);
            tcpProxy.start();

            RmiService rmiService = new RmiService(t,gson);
            rmiService.start();

            URI uri = new URI("rmi:service");
            Route target = new Route(uri);

            Call call = new Call(target, "register",Constante.desktopName, this);
            call.send(t);

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public synchronized void shutDownServer(){
    }


    public static synchronized Server getFirstInstance(){
        if (instance==null){
            instance=new Server();
            instance.startServer();
        }
        return instance;
    }

    public static synchronized Server getInstance(){
        return instance;
    }

    @RMI
    public Boolean addNewProducts(List<ProductBeans> productBeansesList){
        return Logic.getInstance().addProducts(productBeansesList);
    }

    @RMI
    public void returnString(String value, RpcError error) {
        System.out.print(value);
    }

    @RMI
    public String test(String text){
        System.out.println(text);
        return "Salut! Eu sunt un server si vorbesc cu un Android!";
    }

    @RMI
    public List<String> getDepozit(){
        List<Depozit> allDepozit = DataBaseLogic.getInstance().getAllDepozit();
        List<String> list=new ArrayList<String>();
        for (Depozit depozit : allDepozit) {
            list.add(depozit.getNume());
        }

        return list;
    }
}


