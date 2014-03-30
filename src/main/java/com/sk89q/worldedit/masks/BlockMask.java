package com.sk89q.worldedit.masks;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Extent;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.blocks.BaseBlock;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class BlockMask extends ExtentAwareMask {

    private final Set<BaseBlock> blocks;

    public BlockMask() {
        blocks = new HashSet<BaseBlock>();
    }

    public BlockMask(Set<BaseBlock> types) {
        this.blocks = types;
    }

    public BlockMask(BaseBlock... block) {
        blocks = new HashSet<BaseBlock>();
        for (BaseBlock b : block) {
            add(b);
        }
    }

    public BlockMask(BaseBlock block) {
        this();
        add(block);
    }

    public void add(BaseBlock block) {
        blocks.add(block);
    }

    public void addAll(Collection<BaseBlock> blocks) {
        blocks.addAll(blocks);
    }

    @Override
    public boolean matches(EditSession editSession, Vector pos) {
        Extent extent = getExtent(editSession);
        BaseBlock block = extent.getBlock(pos);
        return blocks.contains(block)
                || blocks.contains(new BaseBlock(block.getType(), -1));
    }

}
