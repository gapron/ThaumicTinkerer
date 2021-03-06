package vazkii.tinkerer.common.dim;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Random;

public class OreClusterGenerator implements IWorldGenerator{

	ArrayList<ItemStack> oreIds=null;

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		if(oreIds==null){
			oreIds=getOreIds();
		}
		if (world.provider instanceof WorldProviderBedrock) {
			for (int k = 0; k < 6; k++) {
				int firstBlockXCoord = 16 * chunkX + random.nextInt(16);
				int firstBlockZCoord = 16 * chunkZ + random.nextInt(16);
				ItemStack itemStack = oreIds.get(random.nextInt(oreIds.size()));
				for(int l=0; l<200; l++){
					int firstBlockYCoord = random.nextInt(245)+6;
					WorldGenMinable mineable = new WorldGenMinable(itemStack.itemID,itemStack.getItemDamage(), random.nextInt(20), 7);
					mineable.generate(world, random, firstBlockXCoord, firstBlockYCoord, firstBlockZCoord);
				}
			}
		}
	}

	private ArrayList<ItemStack> getOreIds(){

		Random r = new Random();
		ArrayList<ItemStack> result = new ArrayList<ItemStack>();
		for(String s: OreDictionary.getOreNames()){
			if(s.contains("ore") && !OreDictionary.getOres(s).isEmpty() && OreDictionary.getOres(s).get(0).getItem() instanceof ItemBlock){
				result.add((OreDictionary.getOres(s).get(0)));
			}
		}
		return result;
	}

}
