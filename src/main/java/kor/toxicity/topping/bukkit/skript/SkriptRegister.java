package kor.toxicity.topping.bukkit.skript;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.expressions.base.EventValueExpression;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.parser.ParserInstance;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import com.google.gson.JsonParser;
import kor.toxicity.topping.ToppingClient;
import kor.toxicity.topping.ToppingProduct;
import kor.toxicity.topping.ToppingRelease;
import kor.toxicity.topping.bukkit.event.ToppingConnectEvent;
import kor.toxicity.topping.bukkit.skript.effect.EffAsyncRequest;
import kor.toxicity.topping.bukkit.skript.event.EvtAsyncRequest;
import kor.toxicity.topping.bukkit.skript.expression.*;
import org.jetbrains.annotations.NotNull;

public final class SkriptRegister {
    private SkriptRegister() {
        throw new RuntimeException();
    }

    public static void load() {
        Classes.registerClass(new ClassInfo<>(ToppingProduct.class,"toppingproduct")
                .user("toppingproduct?")
                .name("ToppingProduct")
                .description("Represents a product information of Topping")
                .examples("latest version of \"toppingproduct\"")
                .defaultExpression(new EventValueExpression<>(ToppingProduct.class))
                .parser(new Parser<ToppingProduct>() {
                    @Override
                    public @NotNull String toString(ToppingProduct o, int flags) {
                        return o.toString();
                    }

                    @Override
                    public @NotNull String toVariableNameString(ToppingProduct o) {
                        return "toppingproduct:" + o.getId();
                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public ToppingProduct parse(@NotNull String s, @NotNull ParseContext context) {
                        try {
                            return ToppingClient.getProductInfo(JsonParser.parseString(s).getAsJsonObject());
                        } catch (Exception e) {
                            return null;
                        }
                    }

                    @Override
                    public ToppingProduct parse(@NotNull String s, @NotNull ParseContext context, @NotNull ParserInstance pi) {
                        try {
                            return ToppingClient.getProductInfo(JsonParser.parseString(s).getAsJsonObject());
                        } catch (Exception e) {
                            return null;
                        }
                    }

                    @Override
                    public @NotNull String getVariableNamePattern() {
                        return "toppingproduct:id";
                    }
                }));
        Classes.registerClass(new ClassInfo<>(ToppingRelease.class,"toppingrelease")
                .user("toppingrelease?")
                .name("ToppingProduct")
                .description("Represents a release information of Topping")
                .examples("id of \"toppingrelease\"")
                .defaultExpression(new EventValueExpression<>(ToppingRelease.class))
                .parser(new Parser<ToppingRelease>() {
                    @Override
                    public @NotNull String toString(ToppingRelease o, int flags) {
                        return o.toString();
                    }

                    @Override
                    public @NotNull String toVariableNameString(ToppingRelease o) {
                        return "toppingrelease:" + o.getId();
                    }

                    @Override
                    public boolean canParse(@NotNull ParseContext context) {
                        return true;
                    }

                    @Override
                    public ToppingRelease parse(@NotNull String s, @NotNull ParseContext context) {
                        try {
                            return new ToppingRelease(JsonParser.parseString(s).getAsJsonObject());
                        } catch (Exception e) {
                            return null;
                        }
                    }

                    @Override
                    public ToppingRelease parse(@NotNull String s, @NotNull ParseContext context, @NotNull ParserInstance pi) {
                        try {
                            return new ToppingRelease(JsonParser.parseString(s).getAsJsonObject());
                        } catch (Exception e) {
                            return null;
                        }
                    }

                    @Override
                    public @NotNull String getVariableNamePattern() {
                        return "toppingrelease:id";
                    }
                }));
        Skript.registerExpression(
                ExprToppingProduct.class,
                ToppingProduct.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] request for [the] user id [of] %integer% and [the] product id [of] %integer% [from topping]"
        );
        Skript.registerEffect(
                EffAsyncRequest.class,
                "call request for [the] user id [of] %integer% and [the] product id [of] %integer% [from topping]"
        );
        Skript.registerExpression(
                ExprProductID.class,
                Long.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] id of %toppingproduct%"
        );
        Skript.registerExpression(
                ExprProductPrice.class,
                Double.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] price of %toppingproduct%"
        );
        Skript.registerExpression(
                ExprProductName.class,
                String.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] name of %toppingproduct%"
        );
        Skript.registerExpression(
                ExprProductStatus.class,
                String.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] status of %toppingproduct%"
        );
        Skript.registerExpression(
                ExprProductCreateTime.class,
                String.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] create time of %toppingproduct%"
        );
        Skript.registerExpression(
                ExprProductUpdateTime.class,
                String.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] update time of %toppingproduct%"
        );
        Skript.registerExpression(
                ExprProductDescription.class,
                String.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] description of %toppingproduct%"
        );
        Skript.registerExpression(
                ExprProductLatestRelease.class,
                ToppingRelease.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] latest (release|version) of %toppingproduct%"
        );
        Skript.registerExpression(
                ExprProductRelease.class,
                ToppingRelease.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] (release|version) of %toppingproduct%"
        );

        Skript.registerExpression(
                ExprReleaseID.class,
                Long.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] id of %toppingrelease%"
        );
        Skript.registerExpression(
                ExprReleaseStatus.class,
                String.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] status of %toppingrelease%"
        );
        Skript.registerExpression(
                ExprReleaseFile.class,
                String.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] file of %toppingrelease%"
        );
        Skript.registerExpression(
                ExprReleaseName.class,
                String.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] (name|version) of %toppingrelease%"
        );
        Skript.registerExpression(
                ExprReleaseSize.class,
                Long.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] size of %toppingrelease%"
        );
        Skript.registerExpression(
                ExprReleaseCreateTime.class,
                String.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] create time of %toppingrelease%"
        );
        Skript.registerExpression(
                ExprReleaseUpdateTime.class,
                String.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] update time of %toppingrelease%"
        );
        Skript.registerExpression(
                ExprReleaseDescription.class,
                String.class,
                ExpressionType.COMBINED,
                "[toppingclient] [the] description of %toppingrelease%"
        );
        Skript.registerEvent(
                "Async Topping connect",
                EvtAsyncRequest.class,
                ToppingConnectEvent.class,
                "[toppingclient] async request for product %string% [from topping]"
        );

        EventValues.registerEventValue(
                ToppingConnectEvent.class,
                ToppingProduct.class,
                new Getter<ToppingProduct, ToppingConnectEvent>() {
                    @Override
                    public ToppingProduct get(ToppingConnectEvent arg) {
                        return arg.getProduct();
                    }
                },
                0
        );
    }
}
