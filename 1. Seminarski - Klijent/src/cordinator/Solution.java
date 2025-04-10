/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cordinator;

/**
 *
 * @author andri
 */
class Solution {

    public boolean isPalindrome(String s) {
        String novi = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                novi += "";
            } else {
                novi += s.charAt(i);
            }
        }
        for (int i = 0; i < novi.length() / 2; i++) {
            if (novi.toLowerCase().charAt(i) != novi.toLowerCase().charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

}
