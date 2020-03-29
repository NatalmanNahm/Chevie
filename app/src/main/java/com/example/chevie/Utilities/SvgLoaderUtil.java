package com.example.chevie.Utilities;

import android.content.Context;
import android.widget.ImageView;

import com.example.chevie.R;
import com.pixplicity.sharp.Sharp;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * This is the class that helps to load SVG file as image
 */

public class SvgLoaderUtil {

    private static OkHttpClient httpClient;

    public static void fetchSvg(Context context, String url, final ImageView target) {
        if (httpClient == null) {
            // Use cache for performance and basic offline capability
            httpClient = new OkHttpClient.Builder()
                    .cache(new Cache(context.getCacheDir(), 5 * 1024 * 1014))
                    .build();
        }

        Request request = new Request.Builder().url(url).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream stream = response.body().byteStream();
                Sharp.loadInputStream(stream).into(target);
                stream.close();
            }
        });
    }

    /**
     * Check if the image is an svg or png then use the correct
     * resource to load it into the container.
     * @param container
     * @param image
     * @param context
     */
    public static void imageCheck (ImageView container, String image, Context context){
        if (image.endsWith(".svg")){
            SvgLoaderUtil.fetchSvg(context, image, container);
        } else {
            Picasso.get().load(image).into(container);
        }
    }
}
