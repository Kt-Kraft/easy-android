package easy.app

import android.app.Application
import easy.app.di.appModule
import org.koin.core.context.startKoin

public class MainApplication : Application() {

  override fun onCreate() {
    super.onCreate()
    startKoin {
      modules(appModule)
    }
  }
}
