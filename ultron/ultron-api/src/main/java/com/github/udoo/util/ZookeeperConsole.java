package com.github.udoo.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author dong.yang
 * @data 2019/8/14 10:13
 */
public class ZookeeperConsole {

    private static final String YES = "Y";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CuratorFramework curatorFramework = curatorClient(scanner);
        if (curatorFramework != null) {
            println("zookeeper已经连接，请输入节点路径查询子节点(根节点用'/'表示)");
            showPath(curatorFramework, scanner);
        }
    }

    /**
     * 输入zookeeper address 建立连接
     *
     * @param sc
     * @return
     */
    private static CuratorFramework curatorClient(Scanner sc) {
        println("请输入zookeeper地址(默认localhost:2181):");
        String zkAddress = sc.nextLine();
        if (StrUtil.isBlank(zkAddress)) {
            zkAddress = "localhost:2181";
        }
        println("输入zookeeper地址[" + zkAddress + "]开始建立连接...");
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorClient = CuratorFrameworkFactory.builder()
                .connectString(zkAddress)
                .sessionTimeoutMs(6000)
                .connectionTimeoutMs(3000)
                .retryPolicy(retryPolicy).build();
        curatorClient.start();
        try {
            //尝试连接，判定输入地址是否合法
            boolean connected = curatorClient.blockUntilConnected(10, TimeUnit.SECONDS);
            if (connected) {
                return curatorClient;
            }
            println("连接超时,无效的zookeeper地址: " + zkAddress + " 如需要重试请输入[Y]");
            String retryFlag = sc.nextLine();
            if (YES.equalsIgnoreCase(retryFlag)) {
                return curatorClient(sc);
            }
        } catch (InterruptedException e) {
            System.err.println("连接[" + zkAddress + "]失败: " + e.getMessage());
        }
        return null;
    }

    private static void showPath(CuratorFramework curatorFramework, Scanner scanner) {
        String path = scanner.nextLine();
        while (StrUtil.isBlank(path)) {
            println("需要查看的path不能为空");
            path = scanner.nextLine();
        }
        try {
            List<String> paths = curatorFramework.getChildren().forPath(path);
            if (CollUtil.isEmpty(paths)) {
                println("路径[" + path + "]无子节点。输入[Y]继续");
                String continuedLine = scanner.nextLine();
                if (YES.equalsIgnoreCase(continuedLine)) {
                    showPath(curatorFramework, scanner);
                }
            } else {
                showPathChildrenInfo(curatorFramework, scanner, path);
            }
        } catch (Exception e) {
            System.err.println("出错了： " + e.getMessage());
        }
    }

    private static void showPathChildrenInfo(CuratorFramework curatorFramework, Scanner scanner, String path) throws Exception {
        List<String> paths = curatorFramework.getChildren().forPath(path);
        if (CollUtil.isEmpty(paths)) {
            println("路径[" + URLUtil.decode(path) + "]无子节点。输入[Y]继续");
            String continuedLine = scanner.nextLine();
            if (YES.equalsIgnoreCase(continuedLine)) {
                showPath(curatorFramework, scanner);
            }
        } else {
            println("路径[" + URLUtil.decode(path) + "]包含如下子节点: ");
            int len = paths.size();
            for (int i = 0; i < len; i++) {
                println("[" + i + "]" + URLUtil.decode(paths.get(i)));
            }
            println("请输入序号: 0" + ((len - 1) > 0 ? ("~" + (len - 1)) : "") + " 进行查看");
            String line = scanner.nextLine();
            try {
                Integer idx = Integer.valueOf(line);
                if (idx < 0 || idx > (len - 1)) {
                    println("输入了无效的序号[" + idx + "]");
                    showPathChildrenInfo(curatorFramework, scanner, path);
                }
                String curPath = URLUtil.decode(paths.get(idx));
                println("你选择了路径[" + curPath + "]. 输入[0]查看子节点, 输入[1]查看数据");
                String selectedPath = path.endsWith("/") ? (path + curPath) : (path + "/" + curPath);
                String operation = scanner.nextLine();
                if ("0".equalsIgnoreCase(operation)) {
                    showPathChildrenInfo(curatorFramework, scanner, selectedPath);
                } else if ("1".equalsIgnoreCase(operation)) {
                    byte[] selectedPathData = curatorFramework.getData().forPath(selectedPath);
                    println("路径[" + selectedPath + "]数据为: " + selectedPathData == null ? "" : new String(selectedPathData));
                }
            } catch (Exception e) {
                System.err.println("查看数据[" + line + "]失败: " + e.getMessage());
                showPathChildrenInfo(curatorFramework, scanner, path);
            }
        }
    }

    private static void println(String msg) {
        System.out.println(msg);
    }
}
