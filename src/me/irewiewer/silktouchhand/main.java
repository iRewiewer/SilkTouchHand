package me.irewiewer.silktouchhand;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin
{
	private static main instance;
	
	public static main getInstance()
	{
		return instance;
	}
	
	@Override
	public void onEnable()
	{
		instance = this;
		
		// make SilkTouchHand folder if it doesn't exist
		 try {
			String PATH = System.getProperty("user.dir");
			Path path = Paths.get(PATH + "\\SilkTouchHand\\");

		    //java.nio.file.Files;
		    Files.createDirectories(path);

		  } catch (Exception error) {
		    System.err.println("Error@Line:43;In:main.java: " + error);
		  }
		
		// make blessed file if it doesn't exist
	    try {
			String PATH = System.getProperty("user.dir");
			File myObj = new File(PATH + "\\SilkTouchHand\\blessed.yml");
	    	
	    	if (myObj.createNewFile())
	    	{
	    		// Initialise file with 0 blessed players
	    		try(FileWriter fileWriter = new FileWriter(PATH + "\\SilkTouchHand\\blessed.yml")) {
	    		    fileWriter.write("0");
	    		    fileWriter.close();
	    		} catch (Exception error) {
	    			System.out.println("Error@Line:58;In:main.java: " + error);
	    		}
	    		
    			System.out.println("First time file created: " + myObj.getName());
	    	}
	    } catch (Exception error) {
	    	  System.out.println("Error@Line:64;In:main.java: " + error);
	    }
	    
		getServer().getPluginManager().registerEvents((Listener) new EventListener(), this);
	}
	
	@Override
	public void onDisable()
	{
		instance = null;
	}

	// all enchants maxed
	public ItemStack godEnchant(ItemStack item)
	{
		item.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 32767);
		item.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 32767);
		item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 32767);
		item.addUnsafeEnchantment(Enchantment.ARROW_KNOCKBACK, 32767);
		item.addUnsafeEnchantment(Enchantment.CHANNELING, 32767);
		item.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 32767);
		item.addUnsafeEnchantment(Enchantment.DAMAGE_ARTHROPODS, 32767);
		item.addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 32767);
		item.addUnsafeEnchantment(Enchantment.DEPTH_STRIDER, 32767);
		item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 32767);
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 32767);
		item.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 32767);
		item.addUnsafeEnchantment(Enchantment.FROST_WALKER, 32767);
		item.addUnsafeEnchantment(Enchantment.IMPALING, 32767);
		item.addUnsafeEnchantment(Enchantment.KNOCKBACK, 32767);
		item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_BLOCKS, 32767);
		item.addUnsafeEnchantment(Enchantment.LOOT_BONUS_MOBS, 32767);
		item.addUnsafeEnchantment(Enchantment.LOYALTY, 32767);
		item.addUnsafeEnchantment(Enchantment.LUCK, 32767);
		item.addUnsafeEnchantment(Enchantment.LURE, 32767);
		item.addUnsafeEnchantment(Enchantment.MENDING, 32767);
		item.addUnsafeEnchantment(Enchantment.MULTISHOT, 32767);
		item.addUnsafeEnchantment(Enchantment.OXYGEN, 32767);
		item.addUnsafeEnchantment(Enchantment.PIERCING, 32767);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 32767);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_EXPLOSIONS, 32767);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 32767);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_FIRE, 32767);
		item.addUnsafeEnchantment(Enchantment.PROTECTION_PROJECTILE, 32767);
		item.addUnsafeEnchantment(Enchantment.QUICK_CHARGE, 32767);
		item.addUnsafeEnchantment(Enchantment.RIPTIDE, 32767);
		item.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 32767);
		item.addUnsafeEnchantment(Enchantment.SOUL_SPEED, 32767);
		item.addUnsafeEnchantment(Enchantment.SWEEPING_EDGE, 32767);
		item.addUnsafeEnchantment(Enchantment.THORNS, 32767);
		item.addUnsafeEnchantment(Enchantment.WATER_WORKER, 32767);
		
		return item;
	}
	
	@SuppressWarnings({"deprecation"})
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{	
		// enchant item in player's hand using godEnchant()
		if(label.equalsIgnoreCase("godenchant"))
		{
			Player player = (Player) sender;
			
			ItemStack item = player.getItemInHand();
			
			item = godEnchant(item);
			
			player.setItemInHand(item);
		}
		
		if(label.equalsIgnoreCase("bless"))
		{

			String[] blessedPlayers = new String[999999];

			// read file with all blessed people
			blessedPlayers = EventListener.readFile();

			// if there are currently no blessed players
			if(Integer.parseInt(blessedPlayers[0]) == 0)
			{
				if(sender instanceof ConsoleCommandSender)
				{
					if(args.length == 1)
					{
						if(Bukkit.getServer().getPlayer(args[0]) != null)
						{
							Player targetPlayer = Bukkit.getPlayer(args[0]);
													
							if(EventListener.checkIfBlessed(targetPlayer))
							{
								sender.sendMessage(ChatColor.GRAY + "That player is already blessed.");
							}
							else
							{
								// increment number of blessed players
								blessedPlayers[0] = Integer.toString(Integer.parseInt(blessedPlayers[0]) + 1);
								// add player to list
								blessedPlayers[Integer.parseInt(blessedPlayers[0])] = targetPlayer.getName();
								
								sender.sendMessage(ChatColor.GRAY + "You have blessed " + targetPlayer.getName() + ".");
								targetPlayer.sendMessage(ChatColor.GOLD + "You have been blessed.");
								
								// Write the updated list
								EventListener.writeFile(blessedPlayers);
							}
						}
						else
						{
							sender.sendMessage(ChatColor.GRAY + "That play isn't online right now.");
						}
					}
					else
					{
						sender.sendMessage(ChatColor.RED + "Must specify a player!");
					}
				}
				else
				{
					if(args.length == 1)
						sender.sendMessage(ChatColor.GRAY + "You're not part of the chosen ones.");
					else sender.sendMessage(ChatColor.RED + "You must specify a player! And you're not part of the blessed ones to begin with!");
				}
			}
			else
			{
				// if an argument is specified (most likely a player)
				if(args.length == 1)
				{
					
					if(Bukkit.getServer().getPlayer(args[0]) != null)
					{
						// get targeted player 
						Player targetPlayer = Bukkit.getPlayer(args[0]);
						
						// search for blessed players in string array
						try
						{
							if(sender instanceof ConsoleCommandSender || EventListener.checkIfBlessed((Player) sender))
							{
								if(EventListener.checkIfBlessed(targetPlayer))
								{
									sender.sendMessage(ChatColor.GRAY + "That player is already blessed.");
								}
								else
								{
									// increment number of blessed players
									blessedPlayers[0] = Integer.toString(Integer.parseInt(blessedPlayers[0]) + 1);
									// add player to list
									blessedPlayers[Integer.parseInt(blessedPlayers[0])] = targetPlayer.getName();
									
									sender.sendMessage(ChatColor.GRAY + "You have blessed " + targetPlayer.getName() + ".");
									targetPlayer.sendMessage(ChatColor.GOLD + "You have been blessed.");
	
									// Write the updated list
									EventListener.writeFile(blessedPlayers);
								}
							}
							else
							{
								sender.sendMessage(ChatColor.GRAY + "You're not part of the blessed ones to begin with.");
							}
						} catch(Exception error) {
							System.out.println("Error@Line:217;In:main.java: " + error);
						}
					}
					else
					{
						sender.sendMessage(ChatColor.GRAY + "That player isn't online right now.");
					}
				}
				else
				{
					if(EventListener.checkIfBlessed((Player) sender))
						sender.sendMessage(ChatColor.RED + "Must specify a player!");
					else
						sender.sendMessage(ChatColor.RED + "You must specify a player! And you're not part of the blessed ones to begin with!");
				}
			}
			
			return true;
		}
		
		if(label.equalsIgnoreCase("blessed"))
		{
			String[] blessedPlayers = null;

			// read file with all blessed people
			blessedPlayers = EventListener.readFile();
			
			if(Integer.parseInt(blessedPlayers[0]) == 0)
			{
				sender.sendMessage(ChatColor.GRAY + "There are currently no chosen ones.");
			}
			else
			{
				if(sender instanceof ConsoleCommandSender)
				{
					sender.sendMessage(ChatColor.GRAY + "You are indeed a part of the chosen ones.");
				}
				else
				{
					boolean isBlessed = false;
					Player targetPlayer = (Player) sender;
					
					
					try
					{
						for(int i = 0; i < Integer.parseInt(blessedPlayers[0]) ; i++)
						{
							// if blessed ; bless him
							// else print to chat
		
							if(blessedPlayers[i] == targetPlayer.getName())
							{
								isBlessed = true;
								sender.sendMessage(ChatColor.GRAY + "You are indeed a part of the chosen ones.");
								break;
							}
						}
						
						// only if not blessed after whole string array is checked
						if(isBlessed == false)
							sender.sendMessage(ChatColor.GRAY + "You're not part of the chosen ones.");

					} catch(Exception error) {
						System.out.println("Error@Line:266;In:main.java: " + error);
					}
				}
			}
			
			return true;
		}
		
		return true;
	}
}