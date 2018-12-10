package de.pattyxdhd.sign.manager;

import com.google.common.collect.Lists;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.text.SimpleDateFormat;
import java.util.List;

public class SignManager {

    private ItemStack itemStack;

    public SignManager(final ItemStack itemStack){
        this.itemStack = itemStack;
    }

    public ItemStack sign(final String name, final String message){

        if(!isSigned()){
            setSigned(true);
        }

        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lore;

        if(itemMeta.getLore() == null){
            lore = Lists.newArrayList();
        }else{
            lore = itemMeta.getLore();
        }

        lore.add("§7" + message.replace('&', '§'));
        lore.add("§7§m-----------------------------------");
        lore.add("§7Signiert von §c" + name + " §7am §c" + fortmatTime(System.currentTimeMillis()));

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public ItemStack unSign(){

        if(isSigned()){
            setSigned(false);
        }

        ItemMeta itemMeta = itemStack.getItemMeta();
        List<String> lore = itemMeta.getLore();

        for(int i = 0;i < 3;i++){
            lore.remove(lore.size()-1);
        }

        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public boolean isSigned(){
        net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound nbtTagCompound;

        if(nms.getTag() != null){
            nbtTagCompound = nms.getTag();
        }else{
            nbtTagCompound = new NBTTagCompound();
            setSigned(false);
        }

        return nbtTagCompound.getBoolean("signed");
    }

    public void setSigned(final boolean signed){
        net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound nbtTagCompound;

        if(nms.getTag() != null){
            nbtTagCompound = nms.getTag();
        }else{
            nbtTagCompound = new NBTTagCompound();
        }

        nbtTagCompound.setBoolean("signed", signed);
        nms.setTag(nbtTagCompound);
        itemStack = CraftItemStack.asCraftMirror(nms);
    }

    private String fortmatTime(final Long millis){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        return simpleDateFormat.format(millis);
    }

}
