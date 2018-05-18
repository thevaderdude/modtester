
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import java.util.Random;
public class BlockContainerTest extends BlockContainer
{
   
    private static boolean hasTileEntity;

    public BlockContainerTest()
    {
        super(Material.rock);
        this.setBlockName("grinder");
        
        setCreativeTab(CreativeTabs.tabDecorations);
        stepSound = soundTypeSnow;
        blockParticleGravity = 1.0F;
        slipperiness = 0.6F;
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        lightOpacity = 20; // cast a light shadow
        setTickRandomly(false);
        useNeighborBrightness = false;
    }
    @Override
    public Item getItemDropped(int x, Random random, int y)
    {
        return Item.getItemFromBlock(TutorialMod.blockTable);
    }

   
           

    @Override
	public boolean onBlockActivated(World worldIn, int x, int y, int z, EntityPlayer player, int meta, float px, float py, float pz) {
    
        if (!worldIn.isRemote)
        {
            player.openGui(BlockSmith.instance, BlockSmith.GUI_ENUM.GRINDER.ordinal(),  worldIn, x, y, z); 
        }
        
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
     // DEBUG
     System.out.println("BlockGrinder createNewTileEntity()");
        return new TileEntityContainerTest();
    }

   

   
    @Override
    public void breakBlock(World worldIn, int x, int y, int z, Block block, int meta) {
    	
    
    {
        if (!hasTileEntity)
        {
            TileEntity tileentity = worldIn.getTileEntity(x, y, z);

            if (tileentity instanceof TileEntityContainerTest)
            {
                InventoryBasic.dropInventoryItems(worldIn, x, y, z 
                      (TileEntityContainerTest) tileentity);
                worldIn.updateComparatorOutputLevel(x, y, z this);
            }
        }

        super.breakBlock(worldIn, pos, state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(BlockSmith.blockGrinder);
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state)
    {
        return getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return getDefaultState().withProperty(FACING, enumfacing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {FACING});
    }

    @SideOnly(Side.CLIENT)
    static final class SwitchEnumFacing
    {
        static final int[] enumFacingArray = new int[EnumFacing.values()
              .length];

        static
        {
            try
            {
                enumFacingArray[EnumFacing.WEST.ordinal()] = 1;
            }
            catch (NoSuchFieldError var4)
            {
                ;
            }

            try
            {
                enumFacingArray[EnumFacing.EAST.ordinal()] = 2;
            }
            catch (NoSuchFieldError var3)
            {
                ;
            }

            try
            {
                enumFacingArray[EnumFacing.NORTH.ordinal()] = 3;
            }
            catch (NoSuchFieldError var2)
            {
                ;
            }

            try
            {
                enumFacingArray[EnumFacing.SOUTH.ordinal()] = 4;
            }
            catch (NoSuchFieldError var1)
            {
                // You should improve the error handling here
            }
        }
    }

	
}    