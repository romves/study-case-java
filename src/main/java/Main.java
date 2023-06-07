import data.DataManager;
import model.*;
import model.promo.*;
import model.user.Guest;
import model.user.Member;
import model.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashMap<String, User> users = DataManager.users;
        HashMap<String, Menu> menus = DataManager.menus;
        HashMap<String, PromoCode> promos = DataManager.promos;
        List<String> commands = new ArrayList<>();
        
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();

            if (command.isEmpty()) {
                break;
            }

            commands.add(command);
        }

        for (String command : commands ) {
            String[] parts = command.split(" ", 4);
            String action = parts[0];

            switch (action) {
                case "CREATE" -> {
                    String type = parts[1];
                    switch (type) {
                        case "MEMBER" -> {
                            String[] memberData = parts[2].split("\\|");
                            String memberId = memberData[0];
                            String memberName = memberData[1];
                            String memberJoinDate = memberData[2];
                            double memberBalance = Double.parseDouble(memberData[3]);
                            if (users.containsKey(memberId)) {
                                System.out.println("CREATE MEMBER FAILED: " + memberId + " IS EXISTS");
                            } else {
                                User member = new Member(memberId, memberName, memberJoinDate, memberBalance);
                                users.put(memberId, member);
                                System.out.printf("%s %s %s\n", "CREATE MEMBER SUCCESS:", memberId, memberName);
                            }
                        }
                        case "GUEST" -> {
                            String[] guestData = parts[2].split("\\|");
                            String guestId = guestData[0];
                            double guestBalance = Double.parseDouble(guestData[1]);
                            if (users.containsKey(guestId)) {
                                System.out.println("CREATE GUEST FAILED: " + guestId + " IS EXISTS");
                            } else {
                                User guest = new Guest(guestId, guestBalance);
                                users.put(guestId, guest);
                                System.out.printf("%s %s\n", "CREATE GUEST SUCCESS:", guestId);
                            }
                        }
                        case "MENU" -> {
                            String menuType = parts[2];
                            String[] menuData = parts[3].split("\\|");
                            String menuId = menuData[0];
                            String menuName = menuData[1];
                            double menuPrice = Double.parseDouble(menuData[2]);
                            switch (menuType) {
                                case "CETAK" -> {
                                    if (menus.containsKey(menuId)) {
                                        System.out.println("CREATE MENU FAILED: " + menuId + " IS EXISTS");
                                    } else {
                                        Menu cetak = new Cetak(menuId, menuName, menuPrice);
                                        menus.put(menuId, cetak);
                                        System.out.printf("%s %s %s\n", "CREATE MENU SUCCESS:", menuId, menuName);
                                    }
                                }
                                case "FOTOKOPI" -> {
                                    boolean isDoubleSided = menuData[3].equals("DS");
                                    if (menus.containsKey(menuId)) {
                                        System.out.println("CREATE MENU FAILED: " + menuId + " IS EXISTS");
                                    } else {
                                        Menu fotokopi = new Fotokopi(menuId, menuName, menuPrice, isDoubleSided);
                                        menus.put(menuId, fotokopi);
                                        System.out.printf("%s %s %s\n", "CREATE MENU SUCCESS:", menuId, menuName);
                                    }
                                }
                            }
                        }
                        case "PROMO" -> {
                            String promoType = parts[2];
                            String[] promoData = parts[3].split("\\|");
                            String promoId = promoData[0];
                            String promoStartDate = promoData[1];
                            String promoEndDate = promoData[2];
                            String promoDiscount = promoData[3].replace("%", "");
                            double promoMinTransaction = Double.parseDouble(promoData[4]);
                            double promoMaxDiscount = Double.parseDouble(promoData[5]);
                            switch (promoType) {
                                case "CASHBACK" -> {
                                    if (promos.containsKey(promoId)) {
                                        System.out.println("CREATE PROMO "+ promoType +" FAILED: " +promoId + " IS EXISTS");
                                    } else {
                                        PromoCode promo = new CashbackPromo(promoId, promoStartDate, promoEndDate, promoDiscount, promoMinTransaction, promoMaxDiscount);
                                        promos.put(promoId, promo);
                                        System.out.printf("%s %s\n", "CREATE PROMO "+ promoType +" SUCCESS:", promoId);
                                    }
                                }
                                case "DELIVERY" -> {
                                    if (promos.containsKey(promoId)) {
                                        System.out.println("CREATE PROMO "+ promoType +" FAILED: " + promoId + " IS EXISTS");
                                    } else {
                                        PromoCode promo2 = new DeliveryPromo(promoId, promoStartDate, promoEndDate, promoDiscount, promoMinTransaction, promoMaxDiscount);
                                        promos.put(promoId, promo2);
                                        System.out.printf("%s %s\n", "CREATE PROMO "+ promoType +" SUCCESS:", promoId);
                                    }
                                }
                                case "DISCOUNT" -> {
                                    if (promos.containsKey(promoId)) {
                                        System.out.println("CREATE PROMO "+ promoType +" FAILED: " + promoId + " IS EXISTS");
                                    } else {
                                        PromoCode promo3 = new DiscountPromo(promoId, promoStartDate, promoEndDate, promoDiscount, promoMinTransaction, promoMaxDiscount);
                                        promos.put(promoId, promo3);
                                        System.out.printf("%s %s\n", "CREATE PROMO "+ promoType +" SUCCESS:", promoId);
                                    }
                                }
                            }
                        }
                    }
                }
                case "ADD_TO_CART" -> {
                    String userId = parts[1];
                    String menuId = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    if (users.containsKey(userId)) {
                        User member = users.get(userId);
                        if (menus.containsKey(menuId)) {
                            Menu menu = menus.get(menuId);
                            member.addToCart(menu, quantity);
                        } else {
                            System.out.println("ADD_TO_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                        }
                    } else if (users.containsKey(userId)) {
                        User guest = users.get(userId);
                        if (menus.containsKey(menuId)) {
                            Menu menu = menus.get(menuId);
                            guest.addToCart(menu, quantity);
                        } else {
                            System.out.println("ADD_TO_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                        }
                    } else {
                        System.out.println("ADD_TO_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                    }
                }
                case "REMOVE_FROM_CART" -> {
                    String userId = parts[1];
                    String menuId = parts[2];
                    int quantity = Integer.parseInt(parts[3]);
                    if (users.containsKey(userId)) {
                        User member = users.get(userId);
                        if (menus.containsKey(menuId)) {
                            Menu menu = menus.get(menuId);
                            member.removeFromCart(menu, quantity);
                        } else {
                            System.out.println("REMOVE_FROM_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                        }
                    } else if (users.containsKey(userId)) {
                        User guest = users.get(userId);
                        if (menus.containsKey(menuId)) {
                            Menu menu = menus.get(menuId);
                            guest.removeFromCart(menu, quantity);
                        } else {
                            System.out.println("REMOVE_FROM_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                        }
                    } else {
                        System.out.println("REMOVE_FROM_CART FAILED: NON EXISTENT CUSTOMER OR MENU");
                    }
                }
                case "TOPUP" -> {
                    String userId = parts[1];
                    double amount = Double.parseDouble(parts[2]);
                    if (users.containsKey(userId)) {
                        User member = users.get(userId);
                        member.balanceTopup(amount);
                    } else if (users.containsKey(userId)) {
                        User guest = users.get(userId);
                        guest.balanceTopup(amount);
                    } else {
                        System.out.println("TOPUP FAILED: NON EXISTENT CUSTOMER");
                    }
                }
                case "PRINT" -> {
                    String userId = parts[1];
                    if (users.containsKey(userId)) {
                        User member = users.get(userId);
                        member.viewCartItems();
                    } else if (users.containsKey(userId)) {
                        User guest = users.get(userId);
                        guest.viewCartItems();
                    } else {
                        System.out.println("PRINT FAILED: NON EXISTENT CUSTOMER");
                    }
                }
                case "APPLY_PROMO" -> {
                    String userId = parts[1];
                    String promoId = parts[2];
                    if (users.containsKey(userId)) {
                        User member = users.get(userId);
                        if (promos.containsKey(promoId)) {
                            PromoCode promo = promos.get(promoId);
                            ((Member)member).applyPromoCode(promo);
                        } else {
                            System.out.println("APPLY_PROMO FAILED: NON EXISTENT CUSTOMER OR MENU");
                        }
                    } else {
                        System.out.println("APPLY_PROMO FAILED: NON EXISTENT CUSTOMER OR MENU");
                    }
                }
                case "CHECK_OUT" -> {
                    String userId = parts[1];
                    if (users.containsKey(userId)) {
                        User member = users.get(userId);
                        member.checkout();
                    } else if (users.containsKey(userId)) {
                        User guest = users.get(userId);
                        guest.checkout();
                    } else {
                        System.out.println("CHECKOUT FAILED: NON EXISTENT CUSTOMER");
                    }
                }
                case "PRINT_HISTORY" -> {
                    String userId = parts[1];
                    if (users.containsKey(userId)) {
                        User member = users.get(userId);
                        member.viewOrderHistory();
                    } else if (users.containsKey(userId)) {
                        User guest = users.get(userId);
                        guest.viewOrderHistory();
                    } else {
                        System.out.println("PRINT_HISTORY FAILED: NON EXISTENT CUSTOMER");
                    }
                }
                default -> System.out.println("Invalid command");
            }
        }
    }
}