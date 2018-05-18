package thevaderdude.tutorial;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class BlockTable extends Block {
	
	public BlockTable(Material wood) {
		super(wood);
		this.setHardness(2.0F);
		this.setHarvestLevel("tm:BlockTable", 2);
	}
	@Override
	public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int meta, float px, float py, float pz) {
		System.out.println(x +" " + y + " " + z);
		if (worldIn.getBlock(x, y+1, z).equals(Blocks.bookshelf)) {
			System.out.println("yo thats a stack!");
		}
		else {
			System.out.println("Where da stack?");
		}
		return blockConstructorCalled;
		
	}
	

}
