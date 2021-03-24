package fr.jayrex.st;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;
import net.minecraftforge.fml.common.eventhandler.Event;

public class Events {

    public static class Render {

        public static abstract class RenderCustomLivingEvent<T extends EntityLivingBase> extends Event
        {
            private final EntityLivingBase entity;
            private final net.minecraft.client.renderer.entity.Render<T> renderer;
            private final double x;
            private final double y;
            private final double z;

            public RenderCustomLivingEvent(EntityLivingBase entity, net.minecraft.client.renderer.entity.Render<T> renderer, double x, double y, double z)
            {
                this.entity = entity;
                this.renderer = renderer;
                this.x = x;
                this.y = y;
                this.z = z;
            }

            public EntityLivingBase getEntity() { return entity; }
            public net.minecraft.client.renderer.entity.Render<T> getRenderer() { return renderer; }
            public double getX() { return x; }
            public double getY() { return y; }
            public double getZ() { return z; }

            @Cancelable
            public static class Pre<T extends EntityLivingBase> extends RenderCustomLivingEvent<T>
            {
                public Pre(EntityLivingBase entity, net.minecraft.client.renderer.entity.Render<T> renderer, double x, double y, double z){ super(entity, renderer, x, y, z); }
            }

            public static class Post<T extends EntityLivingBase> extends RenderCustomLivingEvent<T>
            {
                public Post(EntityLivingBase entity, net.minecraft.client.renderer.entity.Render<T> renderer, double x, double y, double z){ super(entity, renderer, x, y, z); }
            }

            public abstract static class Specials<T extends EntityLivingBase> extends RenderCustomLivingEvent<T>
            {
                public Specials(EntityLivingBase entity, net.minecraft.client.renderer.entity.Render<T> renderer, double x, double y, double z){ super(entity, renderer, x, y, z); }

                @Cancelable
                public static class Pre<T extends EntityLivingBase> extends Specials<T>
                {
                    public Pre(EntityLivingBase entity, net.minecraft.client.renderer.entity.Render<T> renderer, double x, double y, double z){ super(entity, renderer, x, y, z); }
                }
                public static class Post<T extends EntityLivingBase> extends Specials<T>
                {
                    public Post(EntityLivingBase entity, net.minecraft.client.renderer.entity.Render<T> renderer, double x, double y, double z){ super(entity, renderer, x, y, z); }
                }
            }
        }



    }


}
